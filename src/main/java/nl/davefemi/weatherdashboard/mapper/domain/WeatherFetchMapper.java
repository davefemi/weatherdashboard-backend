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
    private final ApiClientMapper apiClientMapper;

    public WeatherFetchModel mapToModel(ApiClientModel apiClientModel) {
        WeatherFetchModel model = new WeatherFetchModel();
        model.setFetchTimestamp(Instant.now());
        model.setApiClient(apiClientModel);
        return model;
    }

    public WeatherFetchModel mapToModel(WeatherFetchEntity weatherFetchEntity){
        WeatherFetchModel model = new WeatherFetchModel();
        model.setId(weatherFetchEntity.getId());
        model.setFetchTimestamp(weatherFetchEntity.getFetchTimestamp());
        model.setApiClient(apiClientMapper.mapToModel(weatherFetchEntity.getApiClient()));
        return model;
    }


}
