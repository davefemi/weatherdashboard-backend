package nl.davefemi.weatherdashboard.etl.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HourForecastModel {
    private long id;
    private ForecastDayModel forecastday;
    private long timeEpoch;
    private float temperatureC;
    private WeatherConditionModel condition;
    private boolean isDay;
    private float windKph;
    private long windDegree;
    private String windDirection;
    private float pressureMb;
    private float precipitationMm;
    private float snowCm;
    private float humidity;
    private long cloud;
    private float feelsLikeC;
    private float windchillC;
    private float heatindexC;
    private float dewpointC;
    private long chanceOfRain;
    private long chanceOfSnow;
    private float visibilityKm;
    private float gustKph;
    private float uv;
}
