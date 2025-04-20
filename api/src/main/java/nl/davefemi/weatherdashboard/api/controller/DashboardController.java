package nl.davefemi.weatherdashboard.api.controller;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.api.service.DashboardService;
import nl.davefemi.weatherdashboard.data.model.CurrentWeatherModel;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/public/weather")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService service;

    @GetMapping("/fetch-current-weather")
    public String getCurrentWeather(@RequestParam("location") String location) {
        CurrentWeatherModel dto = service.getCurrentWeather(location);
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
}