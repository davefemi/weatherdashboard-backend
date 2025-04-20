package nl.davefemi.weatherdashboard.client.dto;

import lombok.Data;
import nl.davefemi.weatherdashboard.client.dto.component.ForecastExternalDto;
import nl.davefemi.weatherdashboard.client.dto.component.LocationExternalDto;

@Data
public class MarineWeatherExternalDto implements ExternalDto {
    private LocationExternalDto location;
    //Check Dto for consistency with api response
    private ForecastExternalDto forecast;
}
