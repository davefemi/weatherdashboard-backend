package nl.davefemi.weatherdashboard.mapper.entity;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.database.entity.WeatherFetchEntity;
import nl.davefemi.weatherdashboard.domain.model.WeatherFetchModel;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WeatherFetchEntityMapper {
    private final LocationEntityMapper locationEntityMapper;
    private final ApiClientEntityMapper apiClientEntityMapper;

    public WeatherFetchEntity mapToEntity(WeatherFetchModel weatherFetchModel){
        WeatherFetchEntity entity = new WeatherFetchEntity();
        entity.setId(weatherFetchModel.getId());
        entity.setLocation(locationEntityMapper.mapToEntity(weatherFetchModel.getLocation()));
        entity.setFetchTimestamp(weatherFetchModel.getFetchTimestamp());
        entity.setApiClient(apiClientEntityMapper.mapToEntity(weatherFetchModel.getApiClient()));
        entity.setLocalTime(weatherFetchModel.getLocalTime());
        return entity;
    }
}
