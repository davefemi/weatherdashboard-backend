package nl.davefemi.weatherdashboard.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentWeather {
    private String city;
    private String region;
    private String country;
    private float temperature;
}
