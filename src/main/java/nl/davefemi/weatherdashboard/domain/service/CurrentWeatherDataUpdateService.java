package nl.davefemi.weatherdashboard.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.client.CurrentWeatherClient;
import nl.davefemi.weatherdashboard.client.ForecastWeatherClient;
import nl.davefemi.weatherdashboard.database.repository.*;
import nl.davefemi.weatherdashboard.dto.external.CurrentWeatherExternalDto;
import nl.davefemi.weatherdashboard.mapper.CurrentWeatherMapper;
import nl.davefemi.weatherdashboard.mapper.domain.*;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CurrentWeatherDataUpdateService {
    private final CurrentWeatherClient currentWeatherClient;
    private final CurrentWeatherMapper currentWeatherMapper;
    private final CurrentWeatherRepository currentWeatherRepository;

    public void updateCurrentWeatherData(String location){
        CurrentWeatherExternalDto dto =currentWeatherClient.getExternalDto(location);
        currentWeatherRepository
                .save(currentWeatherMapper
                        .mapToCurrentWeatherEntity(currentWeatherMapper
                                .mapToCurrentWeather(dto)));
    }
}
