package nl.davefemi.weatherdashboard.mapper.domain;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.domain.model.WeatherFetchLocationModel;
import nl.davefemi.weatherdashboard.domain.service.registry.LocationRegistry;
import nl.davefemi.weatherdashboard.dto.external.ForecastWeatherExternalDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Component
public class WeatherFetchLocationMapper {
    private final DateTimeFormatter dateTimeFormatter;
    private final LocationRegistry locationRegistry;

    public WeatherFetchLocationModel mapToModel(ForecastWeatherExternalDto forecastWeatherExternalDto, String location, JsonNode rawJsonData) {
        WeatherFetchLocationModel model = new WeatherFetchLocationModel();
        model.setLocation(locationRegistry.getLocationDescprition(location).getLocation());
        model.setLocalTime(LocalDateTime.parse(forecastWeatherExternalDto.getLocation().getLocaltime(), dateTimeFormatter));
        model.setRawJsonData(rawJsonData);
        return model;
    }
}
