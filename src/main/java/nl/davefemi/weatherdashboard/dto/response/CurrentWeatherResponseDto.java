package nl.davefemi.weatherdashboard.dto.response;

import lombok.Data;

@Data
public class CurrentWeatherResponseDto {
    private String name;
    private String region;
    private String country;
    private String tz_id;
    private String localtime;
    private float temp_c;
    private float feelslike_c;
    private long is_day;
    private String condition;
    private float wind_kph;
    private String wind_dir;
    private float precip_mm;
    private float cloud;

}
