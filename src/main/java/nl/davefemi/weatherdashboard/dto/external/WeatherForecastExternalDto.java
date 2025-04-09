package nl.davefemi.weatherdashboard.dto.external;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class WeatherForecastExternalDto implements ExternalDto {
    private ResponseEntity<String> jSon;
}
