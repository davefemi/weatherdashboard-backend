package nl.davefemi.weatherdashboard.client.dto;

import lombok.Data;
import nl.davefemi.weatherdashboard.client.dto.component.CurrentExternalDto;
import nl.davefemi.weatherdashboard.client.dto.component.ForecastExternalDto;
import nl.davefemi.weatherdashboard.client.dto.component.LocationExternalDto;

@Data
public class ForecastWeatherExternalDto implements ExternalDto {
    private LocationExternalDto location;
    private CurrentExternalDto current;
    private ForecastExternalDto forecast;
}
