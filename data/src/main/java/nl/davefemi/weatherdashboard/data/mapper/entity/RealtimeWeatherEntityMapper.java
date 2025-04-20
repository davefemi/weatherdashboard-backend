package nl.davefemi.weatherdashboard.data.mapper.entity;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.data.entity.RealtimeWeatherEntity;
import nl.davefemi.weatherdashboard.data.model.RealtimeWeatherModel;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RealtimeWeatherEntityMapper {
    private final WeatherConditionEntityMapper weatherConditionEntityMapper;
    private final AirQualityEntityMapper airQualityEntityMapper;

    public RealtimeWeatherEntity mapToEntity(RealtimeWeatherModel realtimeWeatherModel) {
        RealtimeWeatherEntity entity = new RealtimeWeatherEntity();
        entity.setLastUpdatedEpoch(realtimeWeatherModel.getLastUpdatedEpoch());
        entity.setTemperatureC(realtimeWeatherModel.getTemperatureC());
        entity.setCondition(weatherConditionEntityMapper.mapToEntity(realtimeWeatherModel.getCondition()));
        entity.setIsDay(realtimeWeatherModel.isDay());
        entity.setWindKph(realtimeWeatherModel.getWindKph());
        entity.setWindDegree(realtimeWeatherModel.getWindDegree());
        entity.setWindDirection(realtimeWeatherModel.getWindDirection());
        entity.setPressureMb(realtimeWeatherModel.getPressureMb());
        entity.setPrecipitationMm(realtimeWeatherModel.getPrecipitationMm());
        entity.setHumidity(realtimeWeatherModel.getHumidity());
        entity.setCloud(realtimeWeatherModel.getCloud());
        entity.setFeelslikeC(realtimeWeatherModel.getFeelslikeC());
        entity.setWindchillC(realtimeWeatherModel.getWindchillC());
        entity.setHeatindexC(realtimeWeatherModel.getHeatindexC());
        entity.setDewpointC(realtimeWeatherModel.getDewpointC());
        entity.setVisibilityKm(realtimeWeatherModel.getVisibilityKm());
        entity.setUv(realtimeWeatherModel.getUv());
        entity.setGustKph(realtimeWeatherModel.getGustKph());
        entity.setAirQuality(airQualityEntityMapper.mapToEntity(realtimeWeatherModel.getAirQuality()));
        return entity;
    }
}
