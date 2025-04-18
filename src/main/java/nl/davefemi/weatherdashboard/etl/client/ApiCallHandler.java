package nl.davefemi.weatherdashboard.etl.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;

@Slf4j
@RequiredArgsConstructor
@Component
public class ApiCallHandler implements Callable<ResponseEntity<String>> {
    private static final int BASE_DELAY = 500;
    private static final int MAX_WAIT = 1000;
    private static final int MAX_RETRIES = 5;
    private static final Set<HttpStatus> RETRY_STATUS = Set.of (
    HttpStatus.REQUEST_TIMEOUT,
    HttpStatus.TOO_MANY_REQUESTS,
    HttpStatus.INTERNAL_SERVER_ERROR,
    HttpStatus.BAD_GATEWAY,
    HttpStatus.SERVICE_UNAVAILABLE,
    HttpStatus.GATEWAY_TIMEOUT);
    private final RestTemplate restTemplate;
    private String apiUrl;
    private int sleepTime;

    public void setSleepTime(int attempts){
        Random random = new Random();
        sleepTime = random.nextInt((int) Math.min(MAX_WAIT, BASE_DELAY * Math.pow(2, attempts)));
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public ResponseEntity<String> call() {
        ResponseEntity<String> response = null;
        for (int attempts = 0; attempts< MAX_RETRIES; attempts++) {
            log.info("Attempt {}" , attempts+1);
            try {
                response = restTemplate.getForEntity(apiUrl, String.class);
                if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null && !response.getBody().isEmpty()) {
                    return response;
                }
                if (!RETRY_STATUS.contains(response.getStatusCode())) {
                    log.warn("Fatal error code after {} attempts: " + response.getStatusCode().value(), attempts+1);
                    return response;
                }

            } catch (RestClientException e) {
                response = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
                log.warn("Fetch failed for attempt {} due to {}", attempts+1, e.getMessage());
            }
            try {
                setSleepTime(attempts);
                log.info("Sleeping for {} miliseconds", sleepTime);
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Interrupted while sleeping", ex);
            }
        }
            log.warn("Api call failed after {} attempts with status code {} ", MAX_RETRIES, response.getStatusCode());
            throw new RuntimeException(response.getStatusCode().toString());
        }

    }

