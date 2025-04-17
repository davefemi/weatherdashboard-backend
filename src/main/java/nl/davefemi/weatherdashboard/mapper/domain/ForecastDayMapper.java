package nl.davefemi.weatherdashboard.mapper.domain;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.database.entity.ForecastDayEntity;
import nl.davefemi.weatherdashboard.domain.model.AstroModel;
import nl.davefemi.weatherdashboard.domain.model.DailyForecastModel;
import nl.davefemi.weatherdashboard.domain.model.ForecastDayModel;
import nl.davefemi.weatherdashboard.domain.model.WeatherFetchModel;
import nl.davefemi.weatherdashboard.dto.external.component.ForecastdayExternalDto;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ForecastDayMapper {
    private final WeatherFetchMapper weatherFetchMapper;

    public ForecastDayModel mapToModel(ForecastdayExternalDto forecastday, AstroModel astro, DailyForecastModel dailyForecast) {
        ForecastDayModel model = new ForecastDayModel();
        model.setForecastDate(LocalDate.parse(forecastday.getDate()));
        model.setAstro(astro);
        model.setDailyForecast(dailyForecast);
        return model;
    }


    public ForecastDayModel mapToModel(ForecastDayEntity entity) {
        ForecastDayModel model = new ForecastDayModel();
        model.setId(entity.getId());
        model.setWeatherFetch(weatherFetchMapper.mapToModel(entity.getWeatherFetch()));
        model.setForecastDate(entity.getForecastDate());
        return model;
    }

}
