package nl.davefemi.weatherdashboard.etl.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.etl.client.ForecastWeatherClient;
import nl.davefemi.weatherdashboard.etl.domain.service.registry.ApiClientRegistry;
import nl.davefemi.weatherdashboard.etl.domain.service.registry.LocationRegistry;
import nl.davefemi.weatherdashboard.etl.domain.service.DashboardService;
import nl.davefemi.weatherdashboard.etl.domain.service.ForecastDataUpdateService;
import nl.davefemi.weatherdashboard.etl.dto.response.CurrentWeatherResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/weather")
@RequiredArgsConstructor
public class DashboardController {
    private final ApiClientRegistry apiClientRegistry;
    private final LocationRegistry locationRegistry;
    private final DashboardService service;
    private final ForecastDataUpdateService forecastDataUpdateService;
    private final ForecastWeatherClient forecastWeatherClient;
    private final DateTimeFormatter dateTimeFormatter;

    @GetMapping("/fetch-current-weather")
    public String getCurrentWeather(@PathParam("location") String location) {
        CurrentWeatherResponseDto dto = service.getCurrentWeather(location);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String isDay = dto.getIs_day() == 1 ? "Yes" : "No";
        return """
                <html>
                <body>
                    <h2>City: %s</h2>
                    <h2>Region: %s</h2>
                    <h2>Country: %s</h2>
                    <h2>Timezone: %s</h2>
                    <h2>Date: %s</h2>
                    <h2>Time: %s</h2>
                    <h2>Is day? %s</h2>
                    <h2>Temperature: %s °C</h2>
                    <h2>Feels like: %s °C</h2>
                    <h2>Weather Condition: %s</h2>
                    <h2>Wind: %s kph</h2>
                    <h2>Wind Direction: %s</h2>
                    <h2>Precipitation: %s mm</h2>
                    <h2>Clouds: %s %%</h2>
                </body>
                </html>
                """.formatted(
                dto.getName(),
                dto.getRegion(),
                dto.getCountry(),
                dto.getTz_id(),
                LocalDateTime.parse(dto.getLocaltime(), dateFormatter).toLocalDate(),
                LocalDateTime.parse(dto.getLocaltime(), dateFormatter).toLocalTime(),
                isDay,
                dto.getTemp_c(),
                dto.getFeelslike_c(),
                dto.getCondition(),
                dto.getWind_kph(),
                dto.getWind_dir(),
                dto.getPrecip_mm(),
                dto.getCloud()
        );
    }

    @GetMapping("/fetch-forecast")
    public String getWeatherForecast(){
//        ResponseEntity<String> response = service.getWeatherForecast(location).getJSon();
//        ForecastWeatherExternalDto dto = service.getForecastWeather(location);
        forecastDataUpdateService.updateForecastWeatherData(
                (ForecastWeatherClient) apiClientRegistry
                        .getApiClientDescription(forecastWeatherClient)
                        .getApiClient());
        return """
                <html>
                <body>
                    %s
                </body>
                </html>
                """.formatted("dto.getLocation().getName()");
    }

}