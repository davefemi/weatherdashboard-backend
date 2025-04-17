package nl.davefemi.weatherdashboard.etl.client;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.etl.domain.service.registry.ApiClientInfo;
import nl.davefemi.weatherdashboard.etl.dto.external.CurrentWeatherExternalDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@ApiClientInfo(name = "weatherapi", endpoint = "realtime")
@RequiredArgsConstructor
public class CurrentWeatherClient implements ApiClient {
    @Value("${api.weatherapi.key}")
    private String apiKey;
    @Value("${api.weatherapi.url.current-weather}")
    private String apiUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final DateTimeFormatter dateTimeFormatter;

    @SneakyThrows
    @Override
    public CurrentWeatherExternalDto getExternalDto(String response) {
        CurrentWeatherExternalDto dto = objectMapper.readValue(response, CurrentWeatherExternalDto.class);
        return dto;
    }

    @Override
    public String getResponseJson(String location) {
        String url = String.format(apiUrl, apiKey, location);
        ResponseEntity<String> response = restTemplate.getForEntity(String.format(apiUrl, apiKey, location), String.class);
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Failed to fetch weather data");
        }
        log.info("Response {}", response.getBody());
        return response.getBody();
    }
}
