package nl.davefemi.weatherdashboard.mapper.entity;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.database.entity.ForecastDayEntity;
import nl.davefemi.weatherdashboard.database.entity.WeatherFetchLocationEntity;
import nl.davefemi.weatherdashboard.domain.model.ForecastDayModel;
import nl.davefemi.weatherdashboard.domain.model.WeatherFetchLocationModel;
import nl.davefemi.weatherdashboard.mapper.domain.WeatherConditionMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ForecastDayEntityMapper {
    private final WeatherConditionEntityMapper weatherConditionEntityMapper;

    public ForecastDayEntity mapToEntity(ForecastDayModel forecastdayModel) {
        ForecastDayEntity entity = new ForecastDayEntity();
        entity.setForecastDate(forecastdayModel.getForecastDate());
        entity.setSunrise(forecastdayModel.getSunrise());
        entity.setSunset(forecastdayModel.getSunset());
        entity.setMoonrise(forecastdayModel.getMoonrise());
        entity.setMoonset(forecastdayModel.getMoonset());
        entity.setMoonIllumination(forecastdayModel.getMoonIllumination());
        entity.setIsSunUp(forecastdayModel.isSunUp());
        entity.setIsMoonUp(forecastdayModel.isMoonUp());
        entity.setMaxtemperatureC(forecastdayModel.getMaxtemparatureC());
        entity.setMintemperatureC(forecastdayModel.getMintemparatureC());
        entity.setAvgtemperatureC(forecastdayModel.getAvgtemparatureC());
        entity.setMaxwindKph(forecastdayModel.getMaxwindKph());
        entity.setTotalprecipitatonMm(forecastdayModel.getTotalprecipitationMm());
        entity.setTotalsnowCm(forecastdayModel.getTotalsnowCm());
        entity.setAvgvisibilityKm(forecastdayModel.getAvgvisibilityKm());
        entity.setAvghumidity(forecastdayModel.getAvghumdity());
        entity.setChanceOfRain(forecastdayModel.getChanceOfRain());
        entity.setChanceOfSnow(forecastdayModel.getChanceOfSnow());
        entity.setCondition(weatherConditionEntityMapper.mapToEntity(forecastdayModel.getCondition()));
        entity.setUv(forecastdayModel.getUv());
        return entity;
    }
}
