package nl.davefemi.weatherdashboard.dto.external;

import lombok.Data;
import nl.davefemi.weatherdashboard.dto.external.interval.CurrentExternalDto;
import nl.davefemi.weatherdashboard.dto.external.location.LocationExternalDto;

@Data
public class CurrentWeatherExternalDto implements ExternalDto {
    private LocationExternalDto location;
    private CurrentExternalDto current;
}
