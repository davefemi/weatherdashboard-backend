package nl.davefemi.weatherdashboard.dto.external.component;

import lombok.Data;
import java.time.Instant;
import java.time.LocalDateTime;

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
