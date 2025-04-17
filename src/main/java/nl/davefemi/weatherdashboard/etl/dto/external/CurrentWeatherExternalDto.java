package nl.davefemi.weatherdashboard.etl.dto.external;

import lombok.Data;
import nl.davefemi.weatherdashboard.etl.dto.external.component.CurrentExternalDto;
import nl.davefemi.weatherdashboard.etl.dto.external.component.LocationExternalDto;

@Data
public class CurrentWeatherExternalDto implements ExternalDto {
    private LocationExternalDto location;
    private CurrentExternalDto current;
}
