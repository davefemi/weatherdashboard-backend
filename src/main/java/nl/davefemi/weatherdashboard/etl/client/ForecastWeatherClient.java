package nl.davefemi.weatherdashboard.etl.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.etl.domain.service.registry.ApiClientInfo;
import nl.davefemi.weatherdashboard.etl.dto.external.ForecastWeatherExternalDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Component
@ApiClientInfo(name = "weatherapi", endpoint = "forecast")
@Slf4j
public class ForecastWeatherClient implements ApiClient {
    @Value("${api.weatherapi.key}")
    private String apiKey;
    @Value("${api.weatherapi.url.forecast}")
    private String apiUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final DateTimeFormatter dateTimeFormatter;

    @SneakyThrows
    @Override
    public ForecastWeatherExternalDto getExternalDto(String response) {
        ForecastWeatherExternalDto dto = objectMapper.readValue(response, ForecastWeatherExternalDto.class);
        return dto;
    }

    @Override
    public String getResponseJson(String location){
        String url = String.format(apiUrl, apiKey, location);
        ResponseEntity<String> responseString = restTemplate.getForEntity(url, String.class);
        if (!responseString.getStatusCode().is2xxSuccessful() || responseString.getBody() == null) {
            throw new RuntimeException("Failed to fetch weather data");
        }
        log.info("Response {}", responseString.getBody());
        return responseString.getBody();
    }
}

