package nl.davefemi.weatherdashboard.data.mapper.entity;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.data.entity.WeatherFetchLocationEntity;
import nl.davefemi.weatherdashboard.data.model.WeatherFetchLocationModel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherFetchLocationEntityMapper {
    private final LocationEntityMapper locationEntityMapper;
    private final RealtimeWeatherEntityMapper realtimeWeatherEntityMapper;
    private final JsonRawDataEntityMapper jsonRawDataEntityMapper;

    public WeatherFetchLocationEntity mapToEntity(WeatherFetchLocationModel weatherFetchLocationModel) {
        WeatherFetchLocationEntity entity = new WeatherFetchLocationEntity();
        entity.setLocation(locationEntityMapper.mapToEntity(weatherFetchLocationModel.getLocation()));
        entity.setLocalTime(weatherFetchLocationModel.getLocalTime());
        entity.setRealtimeWeather(realtimeWeatherEntityMapper.mapToEntity(weatherFetchLocationModel.getRealtimeWeather()));
        entity.setJsonRawData(jsonRawDataEntityMapper.mapToEntity(weatherFetchLocationModel.getJsonRawData()));
        return entity;
    }
}
