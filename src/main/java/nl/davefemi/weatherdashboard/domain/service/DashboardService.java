package nl.davefemi.weatherdashboard.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.client.CurrentWeatherClient;
import nl.davefemi.weatherdashboard.client.MarineWeatherClient;
import nl.davefemi.weatherdashboard.client.ForecastWeatherClient;
import nl.davefemi.weatherdashboard.database.entity.CurrentWeatherEntity;
import nl.davefemi.weatherdashboard.database.repository.CurrentWeatherRepository;
import nl.davefemi.weatherdashboard.dto.external.CurrentWeatherExternalDto;
import nl.davefemi.weatherdashboard.dto.external.ForecastWeatherExternalDto;
import nl.davefemi.weatherdashboard.dto.external.MarineWeatherExternalDto;
import nl.davefemi.weatherdashboard.dto.response.CurrentWeatherResponseDto;
import nl.davefemi.weatherdashboard.dto.response.WeatherHistoryResponseDto;
import nl.davefemi.weatherdashboard.mapper.CurrentWeatherMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DashboardService {
    private final CurrentWeatherClient currentWeatherClient;
    private final ForecastWeatherClient forecastWeatherClient;
    private final MarineWeatherClient marineWeatherClient;
    private final CurrentWeatherMapper currentWeatherMapper;
    private final CurrentWeatherRepository currentWeatherRepository;

    public CurrentWeatherResponseDto getCurrentWeather(String location)  {
        List<CurrentWeatherEntity> entities =
                currentWeatherRepository.checkForLatestData(Instant.now().plusSeconds(3600), location);
        if(!(entities).isEmpty()){
            log.info("Information younger than 1 hour already exists for this query");
            return currentWeatherMapper
                    .mapToCurrentWeatherResponseDto(currentWeatherMapper
                            .mapToCurrentWeather(entities.get(0)));
        }
        return currentWeatherMapper
                .mapToCurrentWeatherResponseDto(currentWeatherMapper
                        .mapToCurrentWeather(currentWeatherClient
                                .getExternalDto(location)));
    }

    public ForecastWeatherExternalDto getForecastWeather(String location){
        return forecastWeatherClient.getExternalDto(location);
    }

    public WeatherHistoryResponseDto getHistoricalData(String location){
        return null;
    }

    public MarineWeatherExternalDto getForecastMarine(String location){
        return marineWeatherClient.getExternalDto(location);
    }
}
