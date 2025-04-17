package nl.davefemi.weatherdashboard.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationModel {
    private Long id;
    private String name;
    private String region;
    private String country;
    private ContinentModel continent;
    private float lat;
    private float lon;
    private String timezone;
}
