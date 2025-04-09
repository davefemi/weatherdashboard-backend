package nl.davefemi.weatherdashboard.controller;

import java.time.format.DateTimeFormatter;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.dto.CurrentWeatherDto;
import org.springframework.web.bind.annotation.*;
import nl.davefemi.weatherdashboard.client.WeatherClient;

@RestController
@RequestMapping("/public/weather")
@RequiredArgsConstructor
public class DashboardController {
    private final WeatherClient service;

    @GetMapping("/fetch-current-weather")
    public String getCurrentWeather(@PathParam("location") String location) {
        CurrentWeatherDto dto = service.getCurrentWeather(location);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String isDay = dto.isDay() ? "Yes" : "No";
        return """
                <html>
                <body>
                    <h2>City: %s</h2>
                    <h2>Region: %s</h2>
                    <h2>Country: %s</h2>
                    <h2>Timezone: %s</h2>
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
                dto.getCity(),
                dto.getRegion(),
                dto.getCountry(),
                dto.getTimezone(),
                dto.getLocalTime().format(formatter),
                isDay,
                dto.getTemperature(),
                dto.getFeelsLike(),
                dto.getCondition(),
                dto.getWindKph(),
                dto.getWindDirection(),
                dto.getPrecipitationMM(),
                dto.getCloudCoverage()
        );
    }
}