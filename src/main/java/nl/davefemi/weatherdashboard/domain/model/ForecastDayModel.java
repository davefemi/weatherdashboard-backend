package nl.davefemi.weatherdashboard.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ForecastDayModel {
    private long id;
    @ToString.Exclude
    private WeatherFetchModel weatherFetch;
    private LocalDate forecastDate;
    private List<HourForecastModel> hourForecasts = new ArrayList<>();
    private AstroModel astro;
    private DailyForecastModel dailyForecast;
}
