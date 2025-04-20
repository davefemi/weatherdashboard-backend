package nl.davefemi.weatherdashboard.etl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.data.registry.LocationDescription;
import nl.davefemi.weatherdashboard.data.registry.LocationRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class ApiScheduler {
    private final LocationRegistry locationRegistry;
    private final ForecastDataUpdateService forecastDataUpdateService;
    private final CurrentWeatherDataUpdateService currentWeatherDataUpdateService;


    //@Scheduled(cron = "0 */20 * * * *")
    public void updateCurrentWeatherData(){
        log.info("[SCHEDULED] Auto-update initialised:updating current weather data...");
        currentWeatherDataUpdateService.updateCurrentWeatherData("Eindhoven");
    }

    @Scheduled(cron = "0 */10 * * * *")
    public void updateForecastData(){
        Map<String, LocationDescription> locationDescriptions = locationRegistry.getLocations();
        log.info("[SCHEDULED] Auto-update initialised: updating forecast data for {}", locationDescriptions.values());
        forecastDataUpdateService.updateForecastWeatherData();
    }
}
