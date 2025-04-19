package nl.davefemi.weatherdashboard.etl.dto.external.component;

import lombok.Data;

@Data
public class DayExternalDto {
    private float maxtemp_c;
    private float maxtemp_f;
    private float mintemp_c;
    private float mintemp_f;
    private float avgtemp_c;
    private float avgtemp_f;
    private float maxwind_mph;
    private float maxwind_kph;
    private float totalprecip_mm;
    private float totalprecip_in;
    private float totalsnow_cm;
    private float avgvis_km;
    private float avgvis_miles;
    private long avghumidity;
    private long daily_will_it_rain;
    private long daily_chance_of_rain;
    private long daily_will_it_snow;
    private long daily_chance_of_snow;
    private ConditionExternalDto condition;
    private float uv;
}
