package nl.davefemi.weatherdashboard.controller;

import java.time.format.DateTimeFormatter;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.domain.service.DashboardService;
import nl.davefemi.weatherdashboard.dto.external.CurrentWeatherExternalDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/weather")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService service;

    @GetMapping("/fetch-current-weather")
    public String getCurrentWeather(@PathParam("location") String location) {
        CurrentWeatherExternalDto dto = service.getCurrentWeather(location);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String isDay = dto.getCurrent().isDay() ? "Yes" : "No";
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
                dto.getLocation().getCity(),
                dto.getLocation().getRegion(),
                dto.getLocation().getCountry(),
                dto.getLocation().getTimezone(),
                dto.getLocation().getLocalTime().format(formatter),
                isDay,
                dto.getCurrent().getTemperature_C(),
                dto.getCurrent().getFeelsLike_C(),
                dto.getCurrent().getCondition().getText(),
                dto.getCurrent().getWindKph(),
                dto.getCurrent().getWindDirection(),
                dto.getCurrent().getPrecipitationMM(),
                dto.getCurrent().getCloud()
        );
    }

    @GetMapping("/fetch-forecast")
    public String getWeatherForecast(@PathParam("location") String location){
        ResponseEntity<String> response = service.getWeatherForecast(location).getJSon();
        return """
                <html>
                <body>
                    %s
                </body>
                </html>
                """.formatted(response);
    }
}