package nl.davefemi.weatherdashboard.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import nl.davefemi.weatherdashboard.dto.external.CurrentWeatherExternalDto;
import org.springframework.stereotype.Component;

@Component
public class CurrentWeatherMapper {

    public CurrentWeatherExternalDto mapToCurrentWeatherDto(JsonNode jsonNode){
        return null;
    }
}
