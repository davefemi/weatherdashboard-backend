package nl.davefemi.weatherdashboard.dto.external;

import lombok.Data;
import nl.davefemi.weatherdashboard.dto.external.component.*;

@Data
public class ForecastWeatherExternalDto implements ExternalDto {
    private LocationExternalDto location;
    private CurrentExternalDto current;
    private ForecastExternalDto forecast;
}
