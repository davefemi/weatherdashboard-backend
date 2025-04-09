package nl.davefemi.weatherdashboard.utility.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import nl.davefemi.weatherdashboard.dto.CurrentWeatherDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CurrentWeatherMapper {

    public CurrentWeatherDto mapToCurrentWeatherDto(JsonNode jsonNode){
        CurrentWeatherDto dto = new CurrentWeatherDto();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        dto.setCity(jsonNode.path("location").path("name").asText());
        dto.setRegion(jsonNode.path("location").path("region").asText());
        dto.setCountry(jsonNode.path("location").path("country").asText());
        dto.setTimezone(jsonNode.path("location").path("tz_id").asText());
        dto.setLocalTime(LocalDateTime.parse(jsonNode.path("location").path("localtime").asText(), formatter));
        dto.setDay(Integer.parseInt(jsonNode.path("current").path("is_day").asText()));
        dto.setTemperature(Float.parseFloat(jsonNode.path("current").path("temp_c").asText()));
        dto.setFeelsLike(Float.parseFloat(jsonNode.path("current").path("feelslike_c").asText()));
        dto.setCondition(jsonNode.path("current").path("condition").path("text").asText());
        dto.setWindKph(Float.parseFloat(jsonNode.path("current").path("wind_kph").asText()));
        dto.setWindDirection((jsonNode.path("current").path("wind_dir").asText()));
        dto.setPrecipitationMM(Float.parseFloat(jsonNode.path("current").path("precip_mm").asText()));
        dto.setCloudCoverage(Long.parseLong(jsonNode.path("current").path("cloud").asText()));
        return dto;
    }
}
