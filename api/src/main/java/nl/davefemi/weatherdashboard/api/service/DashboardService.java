package nl.davefemi.weatherdashboard.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.api.dto.WeatherHistoryResponseDto;
import nl.davefemi.weatherdashboard.client.api.call.ApiResponse;
import nl.davefemi.weatherdashboard.client.api.client.CurrentWeatherClient;
import nl.davefemi.weatherdashboard.data.entity.CurrentWeatherEntity;
import nl.davefemi.weatherdashboard.data.mapper.CurrentWeatherMapper;
import nl.davefemi.weatherdashboard.data.mapper.domain.ErrorLogMapper;
import nl.davefemi.weatherdashboard.data.model.CurrentWeatherModel;
import nl.davefemi.weatherdashboard.data.repository.CurrentWeatherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.time.Instant;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DashboardService {
    private final CurrentWeatherClient currentWeatherClient;
    private final CurrentWeatherRepository currentWeatherRepository;
    private final CurrentWeatherMapper currentWeatherMapper;
    private final ErrorLogMapper errorLogMapper;

    public CurrentWeatherModel getCurrentWeather(String location) {
        List<CurrentWeatherEntity> entities =
                currentWeatherRepository.checkForLatestData(Instant.now().minusSeconds(900), location);
        if (!entities.isEmpty()) {
            log.warn("Information younger than 15 minutes already exists for this query");
            return currentWeatherMapper
                    .mapToCurrentWeather(entities.getFirst());
        }
        ApiResponse apiResponse = currentWeatherClient.getApiResponse(location);
        if (!apiResponse.isSuccess()) {
            throw new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        CurrentWeatherModel domain = currentWeatherMapper.mapToCurrentWeather(currentWeatherClient
                .getExternalDto(apiResponse.getResponse()));
        currentWeatherRepository.save(currentWeatherMapper.mapToCurrentWeatherEntity(domain));
        return domain;
    }

    public WeatherHistoryResponseDto getHistoricalData(String location){
        return null;
    }

}
