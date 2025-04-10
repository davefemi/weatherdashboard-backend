package nl.davefemi.weatherdashboard.dto.external.component;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ForecastExternalDto {
    private List<ForecastdayExternalDto> forecastday;
}
