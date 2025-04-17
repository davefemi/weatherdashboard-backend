package nl.davefemi.weatherdashboard.mapper.entity;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.database.entity.WeatherFetchEntity;
import nl.davefemi.weatherdashboard.domain.model.WeatherFetchModel;
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
