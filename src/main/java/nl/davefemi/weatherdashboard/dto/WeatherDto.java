package nl.davefemi.weatherdashboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WeatherDto {
    private String city;
    private String region;
    private String country;
    private String timezone;
    private LocalDateTime localTime;
    private int isDay;
    private float temperature;
    private float feelsLike;
    private String condition;
    private float windKph;
    private String windDirection;
    private float precipitationMM;
    private Long cloudCoverage;

    public void setDay(int isDay){
        this.isDay = isDay;
    }

    public boolean isDay(){
        return isDay == 1;
    }



}
