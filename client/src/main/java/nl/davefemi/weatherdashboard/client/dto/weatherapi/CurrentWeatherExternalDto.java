package nl.davefemi.weatherdashboard.client.dto.weatherapi;

import lombok.Data;
import nl.davefemi.weatherdashboard.client.dto.ExternalDto;
import nl.davefemi.weatherdashboard.client.dto.weatherapi.component.CurrentExternalDto;
import nl.davefemi.weatherdashboard.client.dto.weatherapi.component.LocationExternalDto;

@Data
public class CurrentWeatherExternalDto implements ExternalDto {
    private LocationExternalDto location;
    private CurrentExternalDto current;
}
