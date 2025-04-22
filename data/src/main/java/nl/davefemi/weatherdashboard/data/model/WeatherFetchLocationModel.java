package nl.davefemi.weatherdashboard.data.model;

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
    private RealtimeWeatherModel realtimeWeather;
    private List<ForecastDayModel> forecastDays = new ArrayList<>();
    private JsonRawDataModel jsonRawData;
    private ErrorLogModel errorLog;

    public void setRealtimeWeather(RealtimeWeatherModel realtimeWeather) {
        this.realtimeWeather = realtimeWeather;
        realtimeWeather.setWeatherFetchLocation(this);
    }

    public void addForecastDay(ForecastDayModel forecastDay) {
        forecastDays.add(forecastDay);
        forecastDay.setWeatherFetchLocation(this);
    }

    public void setJsonRawData(JsonRawDataModel jsonRawData) {
        this.jsonRawData = jsonRawData;
        jsonRawData.setWeatherFetchLocation(this);
    }

    public void setErrorLog(ErrorLogModel errorLog) {
        this.errorLog = errorLog;
        errorLog.setWeatherFetchLocation(this);
    }
}
