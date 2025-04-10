package nl.davefemi.weatherdashboard.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.dto.external.MarineWeatherExternalDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@Component
@RequiredArgsConstructor
public class MarineWeatherClient implements ApiClient {
    @Value("${api.weatherapi.key}")
    private String apiKey;
    @Value("${api.weatherapi.url.marine}")
    private String apiUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final DateTimeFormatter dateTimeFormatter;

    @SneakyThrows
    @Override
    public MarineWeatherExternalDto getExternalDto(String location) {
        String url = String.format(apiUrl, apiKey, location);
        ResponseEntity<MarineWeatherExternalDto> response = restTemplate.getForEntity(url, MarineWeatherExternalDto.class);
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Failed to fetch weather data");
        }
        log.info("Response {}", response.getBody());
        return response.getBody();
    }
}
