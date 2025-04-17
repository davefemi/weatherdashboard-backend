package nl.davefemi.weatherdashboard.mapper.domain;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.database.entity.WeatherFetchEntity;
import nl.davefemi.weatherdashboard.domain.model.ApiClientModel;
import nl.davefemi.weatherdashboard.domain.model.LocationModel;
import nl.davefemi.weatherdashboard.domain.model.WeatherFetchModel;
import nl.davefemi.weatherdashboard.dto.external.ForecastWeatherExternalDto;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Component
public class WeatherFetchMapper {
    private final LocationMapper locationMapper;
    private final ApiClientMapper apiClientMapper;
    private final DateTimeFormatter dateTimeFormatter;

    public WeatherFetchModel mapToModel(ForecastWeatherExternalDto forecastWeatherExternalDto,
                                        ApiClientModel apiClientModel, LocationModel locationModel ) {
        WeatherFetchModel model = new WeatherFetchModel();
        model.setLocation(locationModel);
        model.setFetchTimestamp(Instant.now());
        model.setApiClient(apiClientModel);
        model.setLocalTime(LocalDateTime.parse(forecastWeatherExternalDto.getLocation().getLocaltime(), dateTimeFormatter));
        return model;
    }

    public WeatherFetchModel mapToModel(WeatherFetchEntity weatherFetchEntity){
        WeatherFetchModel model = new WeatherFetchModel();
        model.setId(weatherFetchEntity.getId());
        model.setFetchTimestamp(weatherFetchEntity.getFetchTimestamp());
        model.setApiClient(apiClientMapper.mapToModel(weatherFetchEntity.getApiClient()));
        model.setLocalTime(weatherFetchEntity.getLocalTime());
        return model;
    }


}
