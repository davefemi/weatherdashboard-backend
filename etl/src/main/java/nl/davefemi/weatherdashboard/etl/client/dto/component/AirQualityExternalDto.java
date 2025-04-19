package nl.davefemi.weatherdashboard.etl.client.dto.component;

import lombok.Data;

@Data
public class AirQualityExternalDto {
    private float co;
    private float no2;
    private float o3;
    private float so2;
    private float pm2_5;
    private float pm10;
    private long us_epa_index;
    private long gb_defra_index;
}
