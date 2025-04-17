package nl.davefemi.weatherdashboard.mapper.entity;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.database.entity.HourForecastEntity;
import nl.davefemi.weatherdashboard.domain.model.HourForecastModel;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HourForecastEntityMapper {
    private final WeatherConditionEntityMapper weatherConditionEntityMapper;

    public HourForecastEntity mapToEntity(HourForecastModel hourForecast) {
        HourForecastEntity entity = new HourForecastEntity();
        entity.setTimeEpoch(hourForecast.getTimeEpoch());
        entity.setTemperatureC(hourForecast.getTemperatureC());
        entity.setCondition(weatherConditionEntityMapper.mapToEntity(hourForecast.getCondition()));
        entity.setIsDay(hourForecast.isDay());
        entity.setWindKph(hourForecast.getWindKph());
        entity.setWindDegree(hourForecast.getWindDegree());
        entity.setWindDirection(hourForecast.getWindDirection());
        entity.setPressureMb(hourForecast.getPressureMb());
        entity.setPrecipitationMm(hourForecast.getPrecipitationMm());
        entity.setSnowCm(hourForecast.getSnowCm());
        entity.setHumidity(hourForecast.getHumidity());
        entity.setCloud(hourForecast.getCloud());
        entity.setFeelslikeC(hourForecast.getFeelsLikeC());
        entity.setWindchillC(hourForecast.getWindchillC());
        entity.setHeatindexC(hourForecast.getHeatindexC());
        entity.setDewpointC(hourForecast.getDewpointC());
        entity.setChanceOfRain(hourForecast.getChanceOfRain());
        entity.setChanceOfSnow(hourForecast.getChanceOfSnow());
        entity.setVisibilityKm(hourForecast.getVisibilityKm());
        entity.setGustKph(hourForecast.getGustKph());
        entity.setUv(hourForecast.getUv());
        return entity;
    }

}
