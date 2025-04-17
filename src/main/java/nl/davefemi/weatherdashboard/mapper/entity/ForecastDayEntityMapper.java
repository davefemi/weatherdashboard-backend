package nl.davefemi.weatherdashboard.mapper.entity;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.database.entity.ForecastDayEntity;
import nl.davefemi.weatherdashboard.domain.model.ForecastDayModel;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ForecastDayEntityMapper {
    private final WeatherFetchEntityMapper weatherFetchEntityMapper;
    private final AstroEntityMapper astroEntityMapper;
    private final DailyForecastEntityMapper dailyForecastEntityMapper;

    public ForecastDayEntity mapToEntity(ForecastDayModel forecastdayModel) {
        ForecastDayEntity entity = new ForecastDayEntity();
        entity.setForecastDate(forecastdayModel.getForecastDate());
        entity.setAstro(astroEntityMapper.mapToEntity(forecastdayModel.getAstro()));
        entity.setDailyForecast(dailyForecastEntityMapper.mapToEntity(forecastdayModel.getDailyForecast()));
        return entity;
    }
}
