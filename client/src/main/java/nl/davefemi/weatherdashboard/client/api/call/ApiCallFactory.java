package nl.davefemi.weatherdashboard.client.api.call;

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

@Component
@Slf4j
@RequiredArgsConstructor
public class ApiCallFactory {
    private final RestTemplate restTemplate;
    private int threadCount = 1;

    public ApiCall createApiCall(String apiUrl, String location) {
        return new ApiCall(getThreadCount(), restTemplate, apiUrl, location);
    }

    private int getThreadCount() {
        int threadCount = this.threadCount;
        this.threadCount++;
        return threadCount;
    }

        @Slf4j
        @RequiredArgsConstructor
        public static class ApiCall implements Callable<ApiResponse> {
            private static final int BASE_DELAY = 1000;
            private static final int MAX_DELAY = 5000;
            private static final int MAX_TRIES = 5;
            private static final Set<HttpStatus> RETRY_STATUS = Set.of (
                    HttpStatus.REQUEST_TIMEOUT,
                    HttpStatus.TOO_MANY_REQUESTS,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.BAD_GATEWAY,
                    HttpStatus.SERVICE_UNAVAILABLE,
                    HttpStatus.GATEWAY_TIMEOUT);
            private final int id;
            private final RestTemplate restTemplate;
            private final Random random = new Random();
            private final String apiUrl;
            private final String location;
            private int sleepTime;

            public ApiResponse call() {
                ResponseEntity<String> response = null;
                for (int attempt = 1; attempt < MAX_TRIES; attempt++) {
                    try {
                        response = restTemplate.getForEntity(apiUrl, String.class);
                        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null && !response.getBody().isEmpty()) {
                            log.info("Api call {} succeeded on attempt {}", id, attempt);
                            return new ApiResponse(true, location, response.getBody());
                        } else if (!RETRY_STATUS.contains(response.getStatusCode())) {
                            log.warn("Fatal error code for api call {} after {} attempts: " + response.getStatusCode().value(), id, attempt);
                            return new ApiResponse(false, location, response.getBody());
                        }
                    } catch (RestClientException e) {
                        HttpStatus code = e instanceof HttpStatusCodeException
                                ? (HttpStatus) ((HttpStatusCodeException) e).getStatusCode()
                                : HttpStatus.SERVICE_UNAVAILABLE;
                        response = ResponseEntity.status(code).body(e.getMessage());
                        log.warn("Api call {} failed for attempt {} due to {}", id, attempt, e.getMessage());
                    }
                    try {
                        setSleepTime(attempt);
                        log.info("Api call {} sleeping for {} milliseconds", id, sleepTime);
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {}
                }
                log.warn("Api call {} failed after {} attempts with status code {} ", id, MAX_TRIES, response.getBody());
                return new ApiResponse(false, location, response.getBody());
            }

            private void setSleepTime(int attempt) {
                sleepTime = random.nextInt((int) Math.min(MAX_DELAY, BASE_DELAY * Math.pow(2, attempt)));
            }
        }
}
