package nl.davefemi.weatherdashboard.etl.dto.external.component;

import lombok.Data;

@Data
public class CurrentExternalDto {
    private long last_updated_epoch;
    private String last_updated;
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
    private float vis_km;
    private float vis_miles;
    private float uv;
    private float gust_mph;
    private float gust_kph;
    private AirQualityExternalDto air_quality;
}
