package nl.davefemi.weatherdashboard.domain.service;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.client.CurrentWeatherClient;
import nl.davefemi.weatherdashboard.client.WeatherForeCastClient;
import nl.davefemi.weatherdashboard.database.repository.CurrentWeatherRepository;
import nl.davefemi.weatherdashboard.dto.external.CurrentWeatherExternalDto;
import nl.davefemi.weatherdashboard.dto.external.WeatherForecastExternalDto;
import nl.davefemi.weatherdashboard.dto.response.WeatherHistoryResponseDto;
import nl.davefemi.weatherdashboard.mapper.CurrentWeatherMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DashboardService {
    private final CurrentWeatherClient currentWeatherClient;
    private final WeatherForeCastClient weatherForeCastClient;
    private final CurrentWeatherMapper currentWeatherMapper;
    private final CurrentWeatherRepository currentWeatherRepository;

    //Will be changed to CurrentWeatherResponseDto
    public CurrentWeatherExternalDto getCurrentWeather(String location)  {
        CurrentWeatherExternalDto dto = currentWeatherClient.getExternalDto(location);
        currentWeatherRepository
                .save(currentWeatherMapper
                        .mapToCurrentWeatherEntity(currentWeatherMapper
                                .mapToCurrentWeather(dto)));
        return dto;
    }

    //Will be changed to WeatherForecastResponseDto
    public WeatherForecastExternalDto getWeatherForecast(String location){
        return weatherForeCastClient.getExternalDto(location);
    }

    public WeatherHistoryResponseDto getHistoricalData(String location){
        return null;
    }
}
