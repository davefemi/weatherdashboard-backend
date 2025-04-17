package nl.davefemi.weatherdashboard.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ForecastDayModel {
    private long id;
    private WeatherFetchLocationModel weatherFetchLocation;
    private LocalDate forecastDate;
    private LocalTime sunrise;
    private LocalTime sunset;
    private LocalTime moonrise;
    private LocalTime moonset;
    private Long moonIllumination;
    private boolean isSunUp;
    private boolean isMoonUp;
    private float maxtemparatureC;
    private float mintemparatureC;
    private float avgtemparatureC;
    private float maxwindKph;
    private float totalprecipitationMm;
    private float totalsnowCm;
    private float avgvisibilityKm;
    private long avghumdity;
    private long chanceOfRain;
    private long chanceOfSnow;
    private WeatherConditionModel condition;
    private float uv;
    private List<HourForecastModel> hourForecasts = new ArrayList<>();

    public void addHourForecast(HourForecastModel hourForecast) {
        hourForecasts.add(hourForecast);
        hourForecast.setForecastday(this);
    }
}
