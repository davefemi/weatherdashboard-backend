package nl.davefemi.weatherdashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import nl.davefemi.weatherdashboard.mapper.MapToDto;
import nl.davefemi.weatherdashboard.dto.CorrectAnswersDto;
import nl.davefemi.weatherdashboard.dto.CurrentWeatherDto;

@Service
public class DashboardService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    public DashboardService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public JsonNode getJsonFromApi(String location) {
        String url = "https://api.weatherapi.com/v1/current.json?key=llo&q={location}&aqi=yes";
        url = url.replace("{location}", location);
        try {
            // Create a new HttpClient instance
            HttpClient client = HttpClient.newHttpClient();
            
            // Build the GET request
            HttpRequest request = HttpRequest.newBuilder()
                                             .uri(URI.create(url))
                                             .GET()
                                             .build();
            
            // Send the request and get the response as a String
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = response.body();
            
            // Parse the JSON string into a JsonNode using Jackson
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CurrentWeatherDto getWeather(String location){
        MapToDto.deserialize(getJsonFromApi("amsterdam"));
       return new CurrentWeatherDto();
    }

    public CorrectAnswersDto getCorrectAnswers(){
        return new CorrectAnswersDto();
    }

}
