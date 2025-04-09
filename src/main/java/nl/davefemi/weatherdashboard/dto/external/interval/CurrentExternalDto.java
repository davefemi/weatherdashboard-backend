package nl.davefemi.weatherdashboard.dto.external.interval;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class CurrentExternalDto {
    private Instant lastUpdatedEpoch;
    private LocalDateTime lastUpdated;
    private float temperature_C;
    private float temperature_F;
    private int isDay;
    private ConditionExternalDto condition;
    private float windMph;
    private float windKph;
    private long windDegree;
    private String windDirection;
    private float pressureMb;
    private float pressureIn;
    private float precipitationMM;
    private float precipiationIn;
    private float humidity;
    private long cloud;
    private float feelsLike_C;
    private float feelsLike_F;
    private float windchill_C;
    private float windchill_F;
    private float heatIndex_C;
    private float heatIndex_F;
    private float dewpoint_C;
    private float dewpoint_F;
    private float visibility_Km;
    private float visibility_Miles;
    private float uv;
    private float gustMph;
    private float gustKph;
    private AirQualityExternalDto airQualityDto;

    public void setDay(int isDay){
        this.isDay = isDay;
    }

    public boolean isDay(){
        return isDay == 1;
    }
}
