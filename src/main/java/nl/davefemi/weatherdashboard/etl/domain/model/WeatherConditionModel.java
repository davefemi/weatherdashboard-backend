package nl.davefemi.weatherdashboard.etl.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherConditionModel {
    private Long code;
    private String text;
}
