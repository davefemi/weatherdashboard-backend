package nl.davefemi.weatherdashboard.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.api.dto.WeatherHistoryResponseDto;
import nl.davefemi.weatherdashboard.client.api.CurrentWeatherClient;
import nl.davefemi.weatherdashboard.data.entity.CurrentWeatherEntity;
import nl.davefemi.weatherdashboard.data.mapper.CurrentWeatherMapper;
import nl.davefemi.weatherdashboard.data.model.CurrentWeatherModel;
import nl.davefemi.weatherdashboard.data.repository.CurrentWeatherRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DashboardService {
    private final CurrentWeatherClient currentWeatherClient;
    private final CurrentWeatherRepository currentWeatherRepository;
    private final CurrentWeatherMapper currentWeatherMapper;

    public CurrentWeatherModel getCurrentWeather(String location) {
        List<CurrentWeatherEntity> entities =
                currentWeatherRepository.checkForLatestData(Instant.now().minusSeconds(900), location);
        if (!entities.isEmpty()) {
            log.warn("Information younger than 15 minutes already exists for this query");
            return currentWeatherMapper
                    .mapToCurrentWeather(entities.getFirst());
        }
        CurrentWeatherModel domain = currentWeatherMapper.mapToCurrentWeather(currentWeatherClient
                .getExternalDto(currentWeatherClient.getResponseJson(location)));
        currentWeatherRepository.save(currentWeatherMapper.mapToCurrentWeatherEntity(domain));
        return domain;
    }

    public WeatherHistoryResponseDto getHistoricalData(String location){
        return null;
    }

}
