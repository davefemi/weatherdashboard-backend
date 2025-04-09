package nl.davefemi.weatherdashboard.controller.exeptions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import java.util.Date;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception exception, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), request);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponse> handeHttpClientError(HttpClientErrorException exception, HttpServletRequest request){
        for (HttpStatus httpStatus: HttpStatus.values()){
            if (httpStatus.value() == exception.getStatusCode().value()){
                log.info("Value of httpStatus: {}", httpStatus.value());
                return buildErrorResponse(httpStatus, extractMessage(exception), request);
            }
        }
        return buildErrorResponse(HttpStatus.NOT_FOUND, extractMessage(exception), request);
    }

    private String extractMessage(HttpClientErrorException errorException){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(errorException.getResponseBodyAsString());
            JsonNode errorNode = jsonNode.has("error") ? jsonNode.get("error") : mapper.nullNode();
            return errorNode.has("message") ? errorNode.get("message").asText() : "Failed to get message";
        } catch (Exception e) {
            log.error("Failed to parse JsonNode");
        }
        return "Unknown error";
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message, HttpServletRequest request){
        return new ResponseEntity<>(new ErrorResponse(
                new Date(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI()
        ), status);
    }
}
