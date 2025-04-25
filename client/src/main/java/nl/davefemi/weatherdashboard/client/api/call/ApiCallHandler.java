package nl.davefemi.weatherdashboard.client.api.call;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class ApiCallHandler {
    private final ApiCallFactory apiCallFactory;

        public List<ApiResponse> getResponses(List<String> locations, String apiUrl, String apiKey) {
        List<ApiResponse> responses = new ArrayList<>();
        ExecutorService pool = Executors.newFixedThreadPool(locations.size());
        List<Future<ApiResponse>> futures;
        List<Callable<ApiResponse>> tasks = new ArrayList<>();
        for (String location : locations) {
            tasks.add(apiCallFactory.createApiCall(String.format(apiUrl, apiKey, location), location)::call);
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


