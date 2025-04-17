package nl.davefemi.weatherdashboard.dto.external.component;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import nl.davefemi.weatherdashboard.client.LocalTimeFallbackDeserializer;

@Data
public class AstroExternalDto {
    @JsonDeserialize(using = LocalTimeFallbackDeserializer.class)
    private String sunrise;

    @JsonDeserialize(using = LocalTimeFallbackDeserializer.class)
    private String sunset;

    @JsonDeserialize(using = LocalTimeFallbackDeserializer.class)
    private String moonrise;

    @JsonDeserialize(using = LocalTimeFallbackDeserializer.class)
    private String moonset;

    private String moon_phase;
    private long moon_illumination;
    private long is_moon_up;
    private long is_sun_up;
}
