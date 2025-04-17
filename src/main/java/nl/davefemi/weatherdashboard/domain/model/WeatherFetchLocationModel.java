package nl.davefemi.weatherdashboard.domain.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WeatherFetchLocationModel {
    private long id;
    private WeatherFetchModel weatherFetch;
    private LocationModel location;
    private LocalDateTime localTime;
    private JsonNode rawJsonData;
    private RealtimeWeatherModel realtimeWeather;
    private List<ForecastDayModel> forecastDays = new ArrayList<>();

    public void setRealtimeWeather(RealtimeWeatherModel realtimeWeather) {
        this.realtimeWeather = realtimeWeather;
        realtimeWeather.setWeatherFetchLocation(this);
    }

    public void addForecastDay(ForecastDayModel forecastDay) {
        forecastDays.add(forecastDay);
        forecastDay.setWeatherFetchLocation(this);
    }
}
