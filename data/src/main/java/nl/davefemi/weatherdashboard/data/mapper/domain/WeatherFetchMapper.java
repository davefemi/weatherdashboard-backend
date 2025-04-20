package nl.davefemi.weatherdashboard.data.mapper.domain;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.data.entity.WeatherFetchEntity;
import nl.davefemi.weatherdashboard.data.model.ApiClientModel;
import nl.davefemi.weatherdashboard.data.model.WeatherFetchModel;
import org.springframework.stereotype.Component;
import java.time.Instant;

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
