package nl.davefemi.weatherdashboard.data.mapper.domain;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.data.model.ErrorLogModel;
import nl.davefemi.weatherdashboard.data.model.LocationModel;
import nl.davefemi.weatherdashboard.data.model.WeatherFetchLocationModel;
import nl.davefemi.weatherdashboard.client.dto.weatherapi.ForecastWeatherExternalDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Component
public class WeatherFetchLocationMapper {
    private final DateTimeFormatter dateTimeFormatter;

    public WeatherFetchLocationModel mapToModel(ForecastWeatherExternalDto forecastWeatherExternalDto, LocationModel location) {
        WeatherFetchLocationModel model = new WeatherFetchLocationModel();
        model.setLocation(location);
        model.setLocalTime(LocalDateTime.parse(forecastWeatherExternalDto.getLocation().getLocaltime(), dateTimeFormatter));
        return model;
    }

    public WeatherFetchLocationModel MapForErrorModel(LocationModel location, ErrorLogModel errorLogModel) {
        WeatherFetchLocationModel model = new WeatherFetchLocationModel();
        model.setLocation(location);
        model.setErrorLog(errorLogModel);
        return model;
    }
}
