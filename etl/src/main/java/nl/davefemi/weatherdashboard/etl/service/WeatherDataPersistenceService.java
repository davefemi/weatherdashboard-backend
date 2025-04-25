package nl.davefemi.weatherdashboard.etl.service;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.data.entity.*;
import nl.davefemi.weatherdashboard.data.mapper.entity.ForecastDayEntityMapper;
import nl.davefemi.weatherdashboard.data.mapper.entity.HourForecastEntityMapper;
import nl.davefemi.weatherdashboard.data.mapper.entity.WeatherFetchEntityMapper;
import nl.davefemi.weatherdashboard.data.mapper.entity.WeatherFetchLocationEntityMapper;
import nl.davefemi.weatherdashboard.data.model.ForecastDayModel;
import nl.davefemi.weatherdashboard.data.model.HourForecastModel;
import nl.davefemi.weatherdashboard.data.model.WeatherFetchLocationModel;
import nl.davefemi.weatherdashboard.data.model.WeatherFetchModel;
import nl.davefemi.weatherdashboard.data.repository.WeatherFetchRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WeatherDataPersistenceService {
    private final WeatherFetchEntityMapper weatherFetchEntityMapper;
    private final WeatherFetchLocationEntityMapper weatherFetchLocationEntityMapper;
    private final ForecastDayEntityMapper forecastDayEntityMapper;
    private final HourForecastEntityMapper hourForecastEntityMapper;
    private final WeatherFetchRepository weatherFetchRepository;

    public void persistWeatherFetchModel(WeatherFetchModel weatherFetchModel) {
        weatherFetchRepository.save(getWeatherFetchEntity(weatherFetchModel));
    }

    private WeatherFetchEntity getWeatherFetchEntity(WeatherFetchModel weatherFetchModel) {
        WeatherFetchEntity weatherFetchEntity = weatherFetchEntityMapper.mapToEntity(weatherFetchModel);
        for (WeatherFetchLocationModel weatherFetchLocationModel : weatherFetchModel.getWeatherFetchLocations()) {
            weatherFetchEntity.addWeatherFetchLocation(getWeatherFetchLocationEntity(weatherFetchLocationModel));
        }
        return weatherFetchEntity;
    }

    private WeatherFetchLocationEntity getWeatherFetchLocationEntity(WeatherFetchLocationModel weatherFetchLocationModel) {
        if (weatherFetchLocationModel.getErrorLog() != null) {
            WeatherFetchLocationEntity weatherFetchLocationEntity = getErrorWeatherFetchLocationEntity(weatherFetchLocationModel);
            return weatherFetchLocationEntity;
        }
        WeatherFetchLocationEntity weatherFetchLocationEntity = weatherFetchLocationEntityMapper.mapToEntity(weatherFetchLocationModel);
        for (ForecastDayModel forecastday : weatherFetchLocationModel.getForecastDays()) {
            weatherFetchLocationEntity.addForecastDay(getForecastDayEntity(forecastday));
        }
        return weatherFetchLocationEntity;
    }

    private WeatherFetchLocationEntity getErrorWeatherFetchLocationEntity(WeatherFetchLocationModel weatherFetchLocationModel) {
        return weatherFetchLocationEntityMapper.mapForErrorEntity(weatherFetchLocationModel);
    }

    private ForecastDayEntity getForecastDayEntity(ForecastDayModel forecastDayModel) {
        ForecastDayEntity forecastDayEntity = forecastDayEntityMapper.mapToEntity(forecastDayModel);
        for (HourForecastModel hourForecast : forecastDayModel.getHourForecasts()) {
            forecastDayEntity.addHourForecast(getHourForecastEntity(hourForecast));
        }
        return forecastDayEntity;
    }

    private HourForecastEntity getHourForecastEntity(HourForecastModel hourForecast) {
        return hourForecastEntityMapper.mapToEntity(hourForecast);
    }
}