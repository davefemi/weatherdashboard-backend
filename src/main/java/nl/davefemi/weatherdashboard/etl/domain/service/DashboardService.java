package nl.davefemi.weatherdashboard.etl.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.etl.client.CurrentWeatherClient;
import nl.davefemi.weatherdashboard.etl.client.ForecastWeatherClient;
import nl.davefemi.weatherdashboard.etl.database.entity.CurrentWeatherEntity;
import nl.davefemi.weatherdashboard.etl.database.repository.CurrentWeatherRepository;
import nl.davefemi.weatherdashboard.etl.domain.model.CurrentWeatherModel;
import nl.davefemi.weatherdashboard.etl.dto.external.ForecastWeatherExternalDto;
import nl.davefemi.weatherdashboard.etl.dto.response.CurrentWeatherResponseDto;
import nl.davefemi.weatherdashboard.etl.dto.response.WeatherHistoryResponseDto;
import nl.davefemi.weatherdashboard.etl.mapper.CurrentWeatherMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DashboardService {
    private final CurrentWeatherClient currentWeatherClient;
    private final ForecastWeatherClient forecastWeatherClient;
    private final CurrentWeatherMapper currentWeatherMapper;
    private final CurrentWeatherRepository currentWeatherRepository;

    public CurrentWeatherResponseDto getCurrentWeather(String location)  {
        List<CurrentWeatherEntity> entities =
                currentWeatherRepository.checkForLatestData(Instant.now().minusSeconds(900), location);
        if(!entities.isEmpty()){
            log.info("Information younger than 15 minutes already exists for this query");
            return currentWeatherMapper
                    .mapToCurrentWeatherResponseDto(currentWeatherMapper
                            .mapToCurrentWeather(entities.getFirst()));
        }
        CurrentWeatherModel domain = currentWeatherMapper.mapToCurrentWeather(currentWeatherClient
                        .getExternalDto(currentWeatherClient.getResponseJson(location)));
        currentWeatherRepository.save(currentWeatherMapper.mapToCurrentWeatherEntity(domain));
        return currentWeatherMapper.mapToCurrentWeatherResponseDto(domain);
    }

    public ForecastWeatherExternalDto getForecastWeather(String location){
        return forecastWeatherClient.getExternalDto(location);
    }

    public WeatherHistoryResponseDto getHistoricalData(String location){
        return null;
    }

}
