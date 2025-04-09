package nl.davefemi.weatherdashboard.client;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.dto.CurrentWeatherDto;
import nl.davefemi.weatherdashboard.utility.mapper.CurrentWeatherMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Service
@Component
@RequiredArgsConstructor
public class WeatherClient {
    @Value("${api.key}")
    private String apiKey;
    @Value("${api.url}")
    private String apiUrl;
    private final RestTemplateBuilder restTemplateBuilder;
    private final ObjectMapper objectMapper;
    private final CurrentWeatherMapper currentWeatherMapper;
    private RestTemplate restTemplate;

    @PostConstruct
    public void init(){
        this.restTemplate = restTemplateBuilder.build();
    }

    @SneakyThrows
    public CurrentWeatherDto getCurrentWeather(String location)  {
        String url = String.format(apiUrl, apiKey, location);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Failed to fetch weather data");
        }
        log.info("Response {}", response.getBody());
        JsonNode root = objectMapper.readTree(response.getBody());
        return currentWeatherMapper.mapToCurrentWeatherDto(root);
    }
}
