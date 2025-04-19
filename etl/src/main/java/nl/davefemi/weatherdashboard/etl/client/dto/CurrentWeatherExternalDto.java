package nl.davefemi.weatherdashboard.etl.client.dto;

import lombok.Data;
import nl.davefemi.weatherdashboard.etl.client.dto.component.CurrentExternalDto;
import nl.davefemi.weatherdashboard.etl.client.dto.component.LocationExternalDto;

@Data
public class CurrentWeatherExternalDto implements ExternalDto {
    private LocationExternalDto location;
    private CurrentExternalDto current;
}
