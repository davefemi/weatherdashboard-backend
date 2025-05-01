package nl.davefemi.weatherdashboard.client.dto.weatherapi.component;

import lombok.Data;

@Data
public class LocationExternalDto {
    private String name;
    private String region;
    private String country;
    private float lat;
    private float lon;
    private String tz_id;
    private long localtime_epoch;
    private String localtime;
}
