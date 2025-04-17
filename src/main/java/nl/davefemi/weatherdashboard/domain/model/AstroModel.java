package nl.davefemi.weatherdashboard.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class AstroModel {
    private long id;
    private LocalTime sunrise;
    private LocalTime sunset;
    private LocalTime moonrise;
    private LocalTime moonset;
    private Long moonIllumination;
    private boolean isSunUp;
    private boolean isMoonUp;

    @Override
    public String toString() {
        return "test";
    }
}
