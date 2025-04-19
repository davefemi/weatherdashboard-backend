package nl.davefemi.weatherdashboard.etl.dto.external;

import lombok.Data;
import nl.davefemi.weatherdashboard.etl.dto.external.component.CurrentExternalDto;
import nl.davefemi.weatherdashboard.etl.dto.external.component.ForecastExternalDto;
import nl.davefemi.weatherdashboard.etl.dto.external.component.LocationExternalDto;

@Data
public class ForecastWeatherExternalDto implements ExternalDto {
    private LocationExternalDto location;
    private CurrentExternalDto current;
    private ForecastExternalDto forecast;
}
