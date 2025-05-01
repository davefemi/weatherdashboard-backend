package nl.davefemi.weatherdashboard.client.dto.weatherapi.component;

import lombok.Data;

import java.util.List;

@Data
public class ForecastExternalDto {
    private List<ForecastdayExternalDto> forecastday;
}
