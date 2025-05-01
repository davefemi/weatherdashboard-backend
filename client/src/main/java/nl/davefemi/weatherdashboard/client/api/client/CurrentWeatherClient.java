package nl.davefemi.weatherdashboard.client.api.client;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.client.api.call.ApiCallHandler;
import nl.davefemi.weatherdashboard.client.api.call.ApiResponse;
import nl.davefemi.weatherdashboard.client.dto.weatherapi.CurrentWeatherExternalDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

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
    public CurrentWeatherExternalDto getExternalDto(String response) {
        CurrentWeatherExternalDto dto = objectMapper.readValue(response, CurrentWeatherExternalDto.class);
        return dto;
    }

    public ApiResponse getApiResponse(String location) {
        ArrayList<String> locations = new ArrayList<>();
        locations.add(location);
        return apiCallHandler.getResponses(locations, apiUrl, apiKey).getFirst();
    }


}
