package nl.davefemi.weatherdashboard.etl.client.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.etl.service.registry.ApiClientInfo;
import nl.davefemi.weatherdashboard.etl.client.dto.ForecastWeatherExternalDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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

