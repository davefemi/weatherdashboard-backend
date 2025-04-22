package nl.davefemi.weatherdashboard.client.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;

@Slf4j
@RequiredArgsConstructor
@Component
public class ApiCallHandler implements Callable<ResponseEntity<String>> {
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
    private final Random random = new Random();
    private String apiUrl;
    private int sleepTime;

    public void setSleepTime(int attempt){
        sleepTime = random.nextInt((int) Math.min(MAX_DELAY, BASE_DELAY * Math.pow(2, attempt)));
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public ResponseEntity<String> call() {
        ResponseEntity<String> response = null;
        for (int attempt = 1 ; attempt <= MAX_RETRIES; attempt++) {
            try {
                response = restTemplate.getForEntity(apiUrl, String.class);
                if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null && !response.getBody().isEmpty()) {
                    log.info("Succeeded on attempt {}" , attempt);
                    return response;
                }
                if (!RETRY_STATUS.contains(response.getStatusCode())) {
                    log.warn("Fatal error code after {} attempts: " + response.getStatusCode().value(), attempt);
                    return response;
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
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Interrupted while sleeping", e);
            }
        }
        log.warn("Api call failed after {} attempts with status code {} ", MAX_RETRIES, response.getBody());
        return response;
//        throw new RuntimeException(response.getStatusCode().toString());
    }
}

