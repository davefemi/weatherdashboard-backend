package nl.davefemi.weatherdashboard.client.api;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.client.dto.CurrentWeatherExternalDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Component
@ApiClientInfo(name = "weatherapi", endpoint = "realtime")
@RequiredArgsConstructor
public class CurrentWeatherClient implements ApiClient {
    @Value("${api.weatherapi.key}")
    private String apiKey;
    @Value("${api.weatherapi.url.current-weather}")
    private String apiUrl;
    private final ObjectMapper objectMapper;
    private final ApiCallHandler apiCallHandler;

    @SneakyThrows
    @Override
    public CurrentWeatherExternalDto getExternalDto(String response) {
        CurrentWeatherExternalDto dto = objectMapper.readValue(response, CurrentWeatherExternalDto.class);
        return dto;
    }

    @Override
    public String getResponseJson(String location) {
        String url = String.format(apiUrl, apiKey, location);
        apiCallHandler.setApiUrl(url);
        ResponseEntity<String> response;
        try {
            response = apiCallHandler.call();
            return response.getBody();
        }
        catch (Exception e) {
            log.error("Failed to fetch weather data", e);
            throw new RuntimeException("Failed to fetch weather data", e);
        }
    }
}
