package nl.davefemi.weatherdashboard.data.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirQualityModel {
    private Long realtimeWeatherId;
    private RealtimeWeatherModel realtimeWeather;
    private float co;
    private float no2;
    private float o3;
    private float so2;
    private float pm2_5;
    private float pm10;
    private long us_epa_index;
    private long gb_defra_index;
}
