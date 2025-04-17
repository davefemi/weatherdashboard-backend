package nl.davefemi.weatherdashboard.mapper.entity;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.database.entity.DailyForecastEntity;
import nl.davefemi.weatherdashboard.database.entity.ForecastDayEntity;
import nl.davefemi.weatherdashboard.database.entity.WeatherFetchEntity;
import nl.davefemi.weatherdashboard.domain.model.DailyForecastModel;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DailyForecastEntityMapper {
    private final WeatherConditionEntityMapper weatherConditionEntityMapper;

    public DailyForecastEntity mapToEntity(DailyForecastModel dailyForecast) {
        DailyForecastEntity entity = new DailyForecastEntity();
        entity.setMaxtemperatureC(dailyForecast.getMaxtemparatureC());
        entity.setMintemperatureC(dailyForecast.getMintemparatureC());
        entity.setAvgtemperatureC(dailyForecast.getAvgtemparatureC());
        entity.setMaxwindKph(dailyForecast.getMaxwindKph());
        entity.setTotalprecipitatonMm(dailyForecast.getTotalprecipitationMm());
        entity.setTotalsnowCm(dailyForecast.getTotalsnowCm());
        entity.setAvgvisibilityKm(dailyForecast.getAvgvisibilityKm());
        entity.setAvghumidity(dailyForecast.getAvghumdity());
        entity.setChanceOfRain(dailyForecast.getChanceOfRain());
        entity.setChanceOfSnow(dailyForecast.getChanceOfSnow());
        entity.setCondition(weatherConditionEntityMapper.mapToEntity(dailyForecast.getCondition()));
        entity.setUv(dailyForecast.getUv());
        return entity;
    }
}
