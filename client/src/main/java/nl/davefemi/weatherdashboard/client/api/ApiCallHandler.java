package nl.davefemi.weatherdashboard.client.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.client.dto.ExternalDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class ApiCallHandler implements Callable<ApiResponse> {
    private static final int BASE_DELAY = 1000;
    private static final int MAX_DELAY = 5000;
    private static final int MAX_RETRIES = 5;
    private static final Set<HttpStatus> RETRY_STATUS = Set.of (
    HttpStatus.REQUEST_TIMEOUT,
    HttpStatus.TOO_MANY_REQUESTS,
    HttpStatus.INTERNAL_SERVER_ERROR,
    HttpStatus.BAD_GATEWAY,
    HttpStatus.SERVICE_UNAVAILABLE,
    HttpStatus.GATEWAY_TIMEOUT);
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final Random random = new Random();
    private String apiUrl;
    private String apiLocation;
    private int sleepTime;

    private ApiCallHandler newApiCallHandler(String apiLocation, String apiUrl) {
        ApiCallHandler clone = new ApiCallHandler(this.restTemplate, this.objectMapper);
        clone.setApiUrl(apiUrl);
        clone.setApiLocation(apiLocation);
        return clone;
    }

    private void setSleepTime(int attempt){
        sleepTime = random.nextInt((int) Math.min(MAX_DELAY, BASE_DELAY * Math.pow(2, attempt)));
    }

    private void setApiLocation(String apiLocation) {
        this.apiLocation = apiLocation;
    }

    private void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public ApiResponse call() {
        ResponseEntity<String> response = null;
        for (int attempt = 1; attempt < MAX_RETRIES; attempt++) {
            try {
                response = restTemplate.getForEntity(apiUrl, String.class);
                if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null && !response.getBody().isEmpty()) {
                    log.info("Succeeded on attempt {}" , attempt);
                    return new ApiResponse(true, apiLocation, response.getBody());
                }
                else if (!RETRY_STATUS.contains(response.getStatusCode())) {
                    log.warn("Fatal error code after {} attempts: " + response.getStatusCode().value(), attempt);
                    return new ApiResponse(false, apiLocation, response.getBody());
                }

            } catch (RestClientException e) {
                HttpStatus code = e instanceof HttpStatusCodeException
                        ? (HttpStatus) ((HttpStatusCodeException) e).getStatusCode()
                        : HttpStatus.SERVICE_UNAVAILABLE;
                response = ResponseEntity.status(code).body(e.getMessage());
                log.warn("Fetch failed for attempt {} due to {}", attempt, e.getMessage());
            }
            try {
                setSleepTime(attempt);
                log.info("Sleeping for {} milliseconds", sleepTime);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {}
        }
        log.warn("Api call failed after {} attempts with status code {} ", MAX_RETRIES, response.getBody());
        return new ApiResponse(false, apiLocation, response.getBody());
        }

        public List<ApiResponse> getResponses(Map<String, String> callProperties) {
        List<ApiResponse> responses = new ArrayList<>();
        ExecutorService pool = Executors.newFixedThreadPool(callProperties.size());
        List<Future<ApiResponse>> futures;
        List<Callable<ApiResponse>> tasks = new ArrayList<>();
        for (Map.Entry<String, String> entry : callProperties.entrySet()) {
            tasks.add(newApiCallHandler(entry.getKey(), entry.getValue())::call);
        }
        try{
            futures = pool.invokeAll(tasks);
            for (Future<ApiResponse> future : futures) {
                responses.add(future.get());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (pool != null) {
                pool.shutdown();
            }
        }
        return responses;
        }
    }


