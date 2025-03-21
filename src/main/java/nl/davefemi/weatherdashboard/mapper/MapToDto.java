package nl.davefemi.weatherdashboard.mapper;

import org.springframework.boot.jackson.JsonComponent;
import com.fasterxml.jackson.databind.JsonNode;

import nl.davefemi.weatherdashboard.dto.CurrentWeatherDto;

@JsonComponent
public class MapToDto {
    
    public static CurrentWeatherDto deserialize(JsonNode json){
        CurrentWeatherDto dto = new CurrentWeatherDto();

        return dto;
    }

    
}
