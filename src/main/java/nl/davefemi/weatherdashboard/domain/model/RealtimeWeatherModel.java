package nl.davefemi.weatherdashboard.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class RealtimeWeatherModel {
    private long id;
    @ToString.Exclude
    private WeatherFetchModel weatherFetch;
    private long lastUpdatedEpoch;
    private float temperatureC;
    private WeatherConditionModel condition;
    private boolean isDay;
    private float windKph;
    private long windDegree;
    private String windDirection;
    private float pressureMb;
    private float precipitationMm;
    private float humidity;
    private long cloud;
    private float feelslikeC;
    private float windchillC;
    private float heatindexC;
    private float dewpointC;
    private float visibilityKm;
    private float uv;
    private float gustKph;
}
