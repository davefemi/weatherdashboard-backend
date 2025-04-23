package nl.davefemi.weatherdashboard.client.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.client.dto.ErrorExternalDto;
import nl.davefemi.weatherdashboard.client.dto.ForecastWeatherExternalDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Component
@ApiClientInfo(name = "weatherapi", endpoint = "forecast")
@Slf4j
public class ForecastWeatherClient implements ApiClient {
    @Value("${api.weatherapi.key}")
    private String apiKey;
    @Value("${api.weatherapi.url.forecast}")
    private String apiUrl;
    private final ApiCallHandler apiCallHandler;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public ForecastWeatherExternalDto getExternalDto(String response) {
        ForecastWeatherExternalDto dto = objectMapper.readValue(response, ForecastWeatherExternalDto.class);
        return dto;
    }

    public ErrorExternalDto getErrorExternalDto(String response) {
        String jSonNode = response.substring(response.indexOf('{'));
        log.info(jSonNode);
        ErrorExternalDto errorExternalDto = new ErrorExternalDto();
        try {
            JsonNode jsonNode = objectMapper.readTree(jSonNode);
            errorExternalDto = objectMapper.treeToValue(jsonNode.path("error"), ErrorExternalDto.class);
            log.info(errorExternalDto.toString());
            return errorExternalDto;
        }
        catch (JsonProcessingException e) {
            log.error(e.getMessage());
            errorExternalDto = new ErrorExternalDto();
            errorExternalDto.setMessage(e.getMessage());
            log.error(errorExternalDto.toString());
            return errorExternalDto;
        }
    }

    public List<ApiResponse> getApiResponse(List<String> locations) {
        HashMap<String, String> urls = new HashMap<>();
        for (String location : locations) {
            urls.put(location, String.format(apiUrl, apiKey, location));
        }
        return apiCallHandler.getResponses(urls);
    }
}

