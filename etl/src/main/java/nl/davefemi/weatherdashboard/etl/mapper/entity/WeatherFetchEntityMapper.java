package nl.davefemi.weatherdashboard.etl.mapper.entity;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.data.entity.WeatherFetchEntity;
import nl.davefemi.weatherdashboard.data.model.WeatherFetchModel;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WeatherFetchEntityMapper {
    private final ApiClientEntityMapper apiClientEntityMapper;

    public WeatherFetchEntity mapToEntity(WeatherFetchModel weatherFetchModel){
        WeatherFetchEntity entity = new WeatherFetchEntity();
        entity.setFetchTimestamp(weatherFetchModel.getFetchTimestamp());
        entity.setApiClient(apiClientEntityMapper.mapToEntity(weatherFetchModel.getApiClient()));
        return entity;
    }
}
