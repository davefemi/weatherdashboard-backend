package nl.davefemi.weatherdashboard.etl.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RealtimeWeatherModel {
    private long id;
    private WeatherFetchLocationModel weatherFetchLocation;
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
    private AirQualityModel airQuality;

    public void setAirQuality(AirQualityModel airQuality) {
        this.airQuality = airQuality;
        airQuality.setRealtimeWeather(this);
    }
}
