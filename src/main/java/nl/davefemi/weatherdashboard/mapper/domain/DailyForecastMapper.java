package nl.davefemi.weatherdashboard.mapper.domain;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.database.entity.DailyForecastEntity;
import nl.davefemi.weatherdashboard.domain.model.DailyForecastModel;
import nl.davefemi.weatherdashboard.domain.model.ForecastDayModel;
import nl.davefemi.weatherdashboard.domain.model.WeatherConditionModel;
import nl.davefemi.weatherdashboard.dto.external.component.DayExternalDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DailyForecastMapper {
    private final WeatherFetchMapper weatherFetchMapper;
    private final ForecastDayMapper forecastDayMapper;
    private final WeatherConditionMapper weatherConditionMapper;

    public DailyForecastModel mapToModel(DayExternalDto dayExternalDto, WeatherConditionModel weatherCondition) {
        DailyForecastModel model = new DailyForecastModel();
        model.setMaxtemparatureC(dayExternalDto.getMaxtemp_c());
        model.setMintemparatureC(dayExternalDto.getMintemp_c());
        model.setAvgtemparatureC(dayExternalDto.getAvgtemp_c());
        model.setMaxwindKph(dayExternalDto.getMaxwind_kph());
        model.setTotalprecipitationMm(dayExternalDto.getTotalprecip_mm());
        model.setTotalsnowCm(dayExternalDto.getTotalsnow_cm());
        model.setAvgvisibilityKm(dayExternalDto.getAvgvis_km());
        model.setAvghumdity(dayExternalDto.getAvghumidity());
        model.setChanceOfRain(dayExternalDto.getDaily_chance_of_rain());
        model.setChanceOfSnow(dayExternalDto.getDaily_chance_of_snow());
        model.setCondition(weatherCondition);
        model.setUv(dayExternalDto.getUv());
        return model;
    }

    public DailyForecastModel mapToModel(DailyForecastEntity dailyForecast) {
        DailyForecastModel model = new DailyForecastModel();
        model.setId(dailyForecast.getId());
        model.setForecastDay(forecastDayMapper.mapToModel(dailyForecast.getForecastDay()));
        model.setMaxtemparatureC(dailyForecast.getMaxtemperatureC());
        model.setMintemparatureC(dailyForecast.getMintemperatureC());
        model.setAvgtemparatureC(dailyForecast.getAvgtemperatureC());
        model.setMaxwindKph(dailyForecast.getMaxwindKph());
        model.setTotalprecipitationMm(dailyForecast.getTotalprecipitatonMm());
        model.setTotalsnowCm(dailyForecast.getTotalsnowCm());
        model.setAvgvisibilityKm(dailyForecast.getAvgvisibilityKm());
        model.setAvghumdity(dailyForecast.getAvghumidity());
        model.setChanceOfRain(dailyForecast.getChanceOfRain());
        model.setChanceOfSnow(dailyForecast.getChanceOfSnow());
        model.setCondition(weatherConditionMapper.mapToModel(dailyForecast.getCondition()));
        model.setUv(dailyForecast.getUv());
        return model;
    }
}
