package nl.davefemi.weatherdashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.davefemi.weatherdashboard.dto.CorrectAnswersDto;
import nl.davefemi.weatherdashboard.dto.CurrentWeatherDto;

@Service
@Component
public class DashboardService {
    @Value("${api.key}")
    private String apiKey;
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    @Autowired
    public DashboardService(RestTemplate restTemplate, ObjectMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    public String getJsonFromApi(String location) throws MalformedURLException, URISyntaxException {
        String url = String.format("https://api.weatherapi.com/v1/current.json?" +
        "key=%s&q=%s&aqi=yes", apiKey, location);

    
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Failed to fetch solar irradiance data");
        }
            
            // Parse the JSON string into a JsonNode using Jackson
            return response.getBody();
    }

    public CurrentWeatherDto getWeather(String location){
        //MapToDto.deserialize(getJsonFromApi("amsterdam"));
       return new CurrentWeatherDto();
    }

    public CorrectAnswersDto getCorrectAnswers(){
        return new CorrectAnswersDto();
    }

}
