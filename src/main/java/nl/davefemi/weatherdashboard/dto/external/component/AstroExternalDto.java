package nl.davefemi.weatherdashboard.dto.external.component;

import lombok.Data;

@Data
public class AstroExternalDto {
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String mooset;
    private String moon_phase;
    private long moon_illumination;
    private long is_moon_up;
    private long is_sun_up;
}
