package nl.davefemi.weatherdashboard.dto.external.location;

import lombok.Data;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class LocationExternalDto {
    private String city;
    private String region;
    private String country;
    private float lat;
    private float lon;
    private String timezone;
    private Instant localTimeEpoch;
    private LocalDateTime localTime;
}
