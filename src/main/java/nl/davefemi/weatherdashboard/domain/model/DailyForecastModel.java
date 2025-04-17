package nl.davefemi.weatherdashboard.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyForecastModel {
    private long id;
    private ForecastDayModel forecastDay;
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
}
