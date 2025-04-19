package nl.davefemi.weatherdashboard.etl.client.dto.component;

import lombok.Data;

import java.util.List;

@Data
public class ForecastExternalDto {
    private List<ForecastdayExternalDto> forecastday;
}
