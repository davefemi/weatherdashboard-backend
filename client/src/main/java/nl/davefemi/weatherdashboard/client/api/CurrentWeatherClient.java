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
        apiCallHandler.setApiUrl(String.format(apiUrl, apiKey, location));
        ResponseEntity<String> response = apiCallHandler.call();
        if (response.getBody() == null || response.getBody().isEmpty() ||!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to fetch weather data " + response.getBody());
        }
        return response.getBody();
    }
}
