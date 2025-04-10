package nl.davefemi.weatherdashboard.dto.external.component;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class HourExternalDto {
    private long time_epoch;
    private String time;
    private float temp_c;
    private float temp_f;
    private long is_day;
    private ConditionExternalDto condition;
    private float wind_mph;
    private float wind_kph;
    private long wind_degree;
    private String wind_dir;
    private float pressure_mb;
    private float pressure_in;
    private float precip_mm;
    private float precip_in;
    private float snow_cm;
    private float humidity;
    private long cloud;
    private float feelslike_c;
    private float feelslike_f;
    private float windchill_c;
    private float windchill_f;
    private float heatindex_c;
    private float heatindex_f;
    private float dewpoint_c;
    private float dewpoint_f;
    private long will_it_rain;
    private long chance_of_rain;
    private long will_it_snow;
    private long chance_of_snow;
    private float vis_km;
    private float vis_miles;
    private float gust_mph;
    private float gust_kph;
    private float uv;
}
