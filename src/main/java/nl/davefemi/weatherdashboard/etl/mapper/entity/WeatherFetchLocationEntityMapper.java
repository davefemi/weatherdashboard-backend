package nl.davefemi.weatherdashboard.etl.mapper.entity;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.etl.database.entity.WeatherFetchLocationEntity;
import nl.davefemi.weatherdashboard.etl.domain.model.WeatherFetchLocationModel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherFetchLocationEntityMapper {
    private final LocationEntityMapper locationEntityMapper;
    private final RealtimeWeatherEntityMapper realtimeWeatherEntityMapper;

    public WeatherFetchLocationEntity mapToEntity(WeatherFetchLocationModel weatherFetchLocationModel) {
        WeatherFetchLocationEntity entity = new WeatherFetchLocationEntity();
        entity.setLocation(locationEntityMapper.mapToEntity(weatherFetchLocationModel.getLocation()));
        entity.setLocalTime(weatherFetchLocationModel.getLocalTime());
        entity.setRawJsonData(weatherFetchLocationModel.getRawJsonData());
        entity.setRealtimeWeather(realtimeWeatherEntityMapper.mapToEntity(weatherFetchLocationModel.getRealtimeWeather()));
        return entity;
    }
}
