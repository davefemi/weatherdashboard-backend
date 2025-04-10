package nl.davefemi.weatherdashboard.dto.external;

import lombok.Data;
import nl.davefemi.weatherdashboard.dto.external.component.ForecastExternalDto;
import nl.davefemi.weatherdashboard.dto.external.component.LocationExternalDto;

@Data
public class MarineWeatherExternalDto implements ExternalDto {
    private LocationExternalDto location;
    //Check Dto for consistency with api response
    private ForecastExternalDto forecast;
}
