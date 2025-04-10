package nl.davefemi.weatherdashboard.domain.service.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.client.CurrentWeatherClient;
import nl.davefemi.weatherdashboard.client.ForecastWeatherClient;
import nl.davefemi.weatherdashboard.client.MarineWeatherClient;
import nl.davefemi.weatherdashboard.database.repository.CurrentWeatherRepository;
import nl.davefemi.weatherdashboard.dto.external.CurrentWeatherExternalDto;
import nl.davefemi.weatherdashboard.mapper.CurrentWeatherMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ApiScheduler {
    private final CurrentWeatherClient currentWeatherClient;
    private final ForecastWeatherClient forecastWeatherClient;
    private final MarineWeatherClient marineWeatherClient;
    private final CurrentWeatherMapper currentWeatherMapper;
    private final CurrentWeatherRepository currentWeatherRepository;

    @Scheduled(cron = "0 */5 * * * *")
    public void updateCurrentWeatherData(){
        log.info("[SCHEDULED] Auto-update initialised:updating current weather data...");
        CurrentWeatherExternalDto dto =currentWeatherClient.getExternalDto("Eindhoven");
        currentWeatherRepository
                .save(currentWeatherMapper
                        .mapToCurrentWeatherEntity(currentWeatherMapper
                                .mapToCurrentWeather(dto)));
    }
}
