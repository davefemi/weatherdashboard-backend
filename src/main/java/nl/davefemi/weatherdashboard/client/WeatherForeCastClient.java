package nl.davefemi.weatherdashboard.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.dto.external.WeatherForecastExternalDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
@Component
@Slf4j
public class WeatherForeCastClient implements ApiClient {
    @Value("${api.weatherapi.key}")
    private String apiKey;
    @Value("${api.weatherapi.url.forecast}")
    private String apiUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public WeatherForecastExternalDto getExternalDto(String location){
        String url = String.format(apiUrl, apiKey, location);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Failed to fetch weather data");
        }
        log.info("Response {}", response.getBody());
        JsonNode node = objectMapper.readTree(response.getBody());
        WeatherForecastExternalDto dto = new WeatherForecastExternalDto();
        dto.setJSon(response);
        log.info("Dto json {}", dto.getJSon());
        return dto;
    }
}
