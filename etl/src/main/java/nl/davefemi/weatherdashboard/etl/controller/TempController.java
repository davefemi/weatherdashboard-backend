package nl.davefemi.weatherdashboard.etl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import nl.davefemi.weatherdashboard.etl.service.ForecastDataUpdateService;

@RestController
@RequestMapping("/public/weather")
@RequiredArgsConstructor
public class TempController {
    private final ForecastDataUpdateService forecastDataUpdateService;


    @GetMapping("/fetch-forecast")
    public String getWeatherForecast(){
        forecastDataUpdateService.updateForecastWeatherData();
        return """
                <html>
                <body>
                    %s
                </body>
                </html>
                """.formatted("dto.getLocation().getName()");
    }

}
