package nl.davefemi.weatherdashboard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.dto.WeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.davefemi.weatherdashboard.dto.CorrectAnswersDto;
import nl.davefemi.weatherdashboard.dto.CurrentWeatherDto;

@Slf4j
@RequiredArgsConstructor
@Service
@Component
public class DashboardService {
    @Value("${api.key}")
    private String apiKey;
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    @SneakyThrows
    public WeatherDto getJsonFromApi(String location)  {
        String url = String.format("https://api.weatherapi.com/v1/current.json?" +
        "key=%s&q=%s&aqi=yes", apiKey, location);

    
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Failed to fetch solar irradiance data");
        }

        log.info("Response {}", response.getBody());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        WeatherDto dto = new WeatherDto();
        JsonNode root = mapper.readTree(response.getBody());
        dto.setCity(root.path("location").path("name").asText());
        dto.setRegion(root.path("location").path("region").asText());
        dto.setCountry(root.path("location").path("country").asText());
        dto.setTimezone(root.path("location").path("tz_id").asText());
        dto.setLocalTime(LocalDateTime.parse(root.path("location").path("localtime").asText(), formatter));
        dto.setDay(Integer.parseInt(root.path("current").path("is_day").asText()));
        dto.setTemperature(Float.parseFloat(root.path("current").path("temp_c").asText()));
        dto.setFeelsLike(Float.parseFloat(root.path("current").path("feelslike_c").asText()));
        dto.setCondition(root.path("current").path("condition").path("text").asText());
        dto.setWindKph(Float.parseFloat(root.path("current").path("wind_kph").asText()));
        dto.setWindDirection((root.path("current").path("wind_dir").asText()));
        dto.setPrecipitationMM(Float.parseFloat(root.path("current").path("precip_mm").asText()));
        dto.setCloudCoverage(Long.parseLong(root.path("current").path("cloud").asText()));
            
            // Parse the JSON string into a JsonNode using Jackson
            return dto;
    }

    public CurrentWeatherDto getWeather(String location){
        //MapToDto.deserialize(getJsonFromApi("amsterdam"));
       return new CurrentWeatherDto();
    }

    public CorrectAnswersDto getCorrectAnswers(){
        return new CorrectAnswersDto();
    }

}
