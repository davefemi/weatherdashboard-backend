package nl.davefemi.weatherdashboard.client.dto.weatherapi;

import lombok.Data;
import nl.davefemi.weatherdashboard.client.dto.ExternalDto;
import nl.davefemi.weatherdashboard.client.dto.weatherapi.component.ForecastExternalDto;
import nl.davefemi.weatherdashboard.client.dto.weatherapi.component.LocationExternalDto;

@Data
public class MarineWeatherExternalDto implements ExternalDto {
    private LocationExternalDto location;
    //Check Dto for consistency with api response
    private ForecastExternalDto forecast;
}
