package nl.davefemi.weatherdashboard.data.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class CurrentWeatherModel {
    private Instant fetchTimestamp;
    private String name;
    private String region;
    private String country;
    private String tz_id;
    private String localtime;
    private float temp_c;
    private long is_day;
    private float feelslike_c;
    private String condition;
    private float wind_kph;
    private String wind_dir;
    private float precip_mm;
    private float cloud;
}
