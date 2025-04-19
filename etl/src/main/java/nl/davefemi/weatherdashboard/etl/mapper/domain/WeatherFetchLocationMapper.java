package nl.davefemi.weatherdashboard.etl.mapper.domain;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.domain.model.WeatherFetchLocationModel;
import nl.davefemi.weatherdashboard.etl.service.registry.LocationRegistry;
import nl.davefemi.weatherdashboard.etl.client.dto.ForecastWeatherExternalDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Component
public class WeatherFetchLocationMapper {
    private final DateTimeFormatter dateTimeFormatter;
    private final LocationRegistry locationRegistry;

    public WeatherFetchLocationModel mapToModel(ForecastWeatherExternalDto forecastWeatherExternalDto, String location) {
        WeatherFetchLocationModel model = new WeatherFetchLocationModel();
        model.setLocation(locationRegistry.getLocationDescprition(location).getLocation());
        model.setLocalTime(LocalDateTime.parse(forecastWeatherExternalDto.getLocation().getLocaltime(), dateTimeFormatter));
        return model;
    }
}
