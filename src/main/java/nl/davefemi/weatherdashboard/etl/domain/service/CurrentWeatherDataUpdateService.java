package nl.davefemi.weatherdashboard.etl.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.etl.client.CurrentWeatherClient;
import nl.davefemi.weatherdashboard.etl.database.repository.CurrentWeatherRepository;
import nl.davefemi.weatherdashboard.etl.dto.external.CurrentWeatherExternalDto;
import nl.davefemi.weatherdashboard.etl.mapper.CurrentWeatherMapper;
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
