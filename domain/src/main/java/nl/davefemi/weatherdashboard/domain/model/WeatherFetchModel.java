package nl.davefemi.weatherdashboard.domain.model;

import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WeatherFetchModel {
    private Long id;
    private Instant fetchTimestamp;
    private ApiClientModel apiClient;
    private List<WeatherFetchLocationModel> weatherFetchLocations = new ArrayList<>();

    public void addWeatherFetchLocation(WeatherFetchLocationModel weatherFetchLocation) {
        weatherFetchLocations.add(weatherFetchLocation);
        weatherFetchLocation.setWeatherFetch(this);
    }
}
