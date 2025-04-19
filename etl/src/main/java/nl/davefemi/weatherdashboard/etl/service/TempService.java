package nl.davefemi.weatherdashboard.etl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.database.entity.CurrentWeatherEntity;
import nl.davefemi.weatherdashboard.database.repository.CurrentWeatherRepository;
import nl.davefemi.weatherdashboard.domain.model.CurrentWeatherModel;
import nl.davefemi.weatherdashboard.etl.client.api.CurrentWeatherClient;
import nl.davefemi.weatherdashboard.etl.client.api.ForecastWeatherClient;
import nl.davefemi.weatherdashboard.etl.client.dto.ForecastWeatherExternalDto;
import nl.davefemi.weatherdashboard.etl.mapper.CurrentWeatherMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TempService {
    private final CurrentWeatherClient currentWeatherClient;
    private final ForecastWeatherClient forecastWeatherClient;
    private final CurrentWeatherMapper currentWeatherMapper;
    private final CurrentWeatherRepository currentWeatherRepository;

    public CurrentWeatherModel getCurrentWeather(String location) {
        List<CurrentWeatherEntity> entities =
                currentWeatherRepository.checkForLatestData(Instant.now().minusSeconds(900), location);
        if (!entities.isEmpty()) {
            log.info("Information younger than 15 minutes already exists for this query");
            return currentWeatherMapper
                    .mapToCurrentWeather(entities.getFirst());
        }
        CurrentWeatherModel domain = currentWeatherMapper.mapToCurrentWeather(currentWeatherClient
                .getExternalDto(currentWeatherClient.getResponseJson(location)));
        currentWeatherRepository.save(currentWeatherMapper.mapToCurrentWeatherEntity(domain));
        return domain;
    }

    public ForecastWeatherExternalDto getForecastWeather(String location) {
        return forecastWeatherClient.getExternalDto(location);
    }
}