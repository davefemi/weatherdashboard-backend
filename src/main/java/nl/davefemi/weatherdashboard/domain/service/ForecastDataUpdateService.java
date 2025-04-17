package nl.davefemi.weatherdashboard.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.client.CurrentWeatherClient;
import nl.davefemi.weatherdashboard.client.ForecastWeatherClient;
import nl.davefemi.weatherdashboard.database.entity.ForecastDayEntity;
import nl.davefemi.weatherdashboard.database.entity.HourForecastEntity;
import nl.davefemi.weatherdashboard.database.entity.WeatherFetchEntity;
import nl.davefemi.weatherdashboard.database.entity.WeatherFetchLocationEntity;
import nl.davefemi.weatherdashboard.domain.model.*;
import nl.davefemi.weatherdashboard.domain.service.registry.*;
import nl.davefemi.weatherdashboard.database.repository.*;
import nl.davefemi.weatherdashboard.dto.external.CurrentWeatherExternalDto;
import nl.davefemi.weatherdashboard.dto.external.ForecastWeatherExternalDto;
import nl.davefemi.weatherdashboard.dto.external.component.*;
import nl.davefemi.weatherdashboard.mapper.CurrentWeatherMapper;
import nl.davefemi.weatherdashboard.mapper.domain.*;
import nl.davefemi.weatherdashboard.mapper.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class ForecastDataUpdateService {
    private final ForecastWeatherClient forecastWeatherClient;
    private final CurrentWeatherClient currentWeatherClient;
    private final CurrentWeatherMapper currentWeatherMapper;
    private final CurrentWeatherRepository currentWeatherRepository;
    private final WeatherFetchMapper weatherFetchMapper;
    private final RealtimeWeatherMapper realtimeWeatherMapper;
    private final ForecastDayMapper forecastDayMapper;
    private final HourForecastMapper hourForecastMapper;
    private final WeatherFetchLocationMapper weatherFetchLocationMapper;
    private final WeatherFetchLocationEntityMapper weatherFetchLocationEntityMapper;
    private final WeatherFetchEntityMapper weatherFetchEntityMapper;
    private final HourForecastEntityMapper hourForecastEntityMapper;
    private final WeatherFetchRepository weatherFetchRepository;
    private final ForecastDayEntityMapper forecastDayEntityMapper;
    private final LocationRegistry locationRegistry;
    private final ApiClientRegistry apiClientRegistry;
    private final ObjectMapper objectMapper;

    public void updateForecastWeatherData(ForecastWeatherClient forecastWeatherClient) {
        WeatherFetchModel weatherFetchModel = getWeatherFetchModel();
        WeatherFetchEntity weatherFetchEntity = getWeatherFetchEntity(weatherFetchModel);
        weatherFetchRepository.save(weatherFetchEntity);
    }

    private WeatherFetchModel getWeatherFetchModel() {
        Map<String, LocationDescription> locationDescriptions = locationRegistry.getLocations();
        ApiClientDescription apiClientDescription = apiClientRegistry.getApiClientDescription(forecastWeatherClient);
        WeatherFetchModel weatherFetchModel = weatherFetchMapper.mapToModel(apiClientDescription.getApiClientModel());
        for (String location : locationDescriptions.keySet()) {
            WeatherFetchLocationModel weatherFetchLocationModel = getWeatherFetchLocationModel(location, weatherFetchModel);
            weatherFetchModel.addWeatherFetchLocation(weatherFetchLocationModel);
        }
        return weatherFetchModel;
    }

    private WeatherFetchLocationModel getWeatherFetchLocationModel(String location, WeatherFetchModel weatherFetchModel) {
        String fetchResponse = forecastWeatherClient.getResponseJson(location);
        ForecastWeatherExternalDto forecastWeatherExternalDto = forecastWeatherClient.getExternalDto(fetchResponse);
        WeatherFetchLocationModel weatherFetchLocationModel;
        JsonNode rawJsonData = null;
        try {
            rawJsonData = objectMapper.readTree(fetchResponse);
        } catch (Exception e) {
            throw new RuntimeException("Could not read Json {}", e);
        }
        weatherFetchLocationModel =
                weatherFetchLocationMapper.mapToModel(
                        forecastWeatherExternalDto,
                        location,
                        rawJsonData);
        weatherFetchLocationModel.setRealtimeWeather(getRealTimeWeatherModel(forecastWeatherExternalDto.getCurrent(), weatherFetchLocationModel));
        for (ForecastdayExternalDto forecastDayExternalDto : forecastWeatherExternalDto.getForecast().getForecastday())
            weatherFetchLocationModel.addForecastDay(getForecastDayModel(forecastDayExternalDto, weatherFetchLocationModel));
        return weatherFetchLocationModel;
    }

    private RealtimeWeatherModel getRealTimeWeatherModel(CurrentExternalDto currentExternalDto, WeatherFetchLocationModel weatherLocationFetchModel) {
        RealtimeWeatherModel realtimeWeatherModel = realtimeWeatherMapper.mapToModel(currentExternalDto);
        return realtimeWeatherModel;
    }

    private ForecastDayModel getForecastDayModel(ForecastdayExternalDto forecastdayExternalDto, WeatherFetchLocationModel weatherFetchLocationModel) {
        ForecastDayModel forecastDayModel = forecastDayMapper.mapToModel(forecastdayExternalDto);
        for (HourExternalDto hourExternalDto : forecastdayExternalDto.getHour()) {
            forecastDayModel.addHourForecast(getHourForecastModel(hourExternalDto, forecastDayModel));
        }
        return forecastDayModel;
    }

    private HourForecastModel getHourForecastModel(HourExternalDto hourExternalDto, ForecastDayModel forecastDayModel) {
        HourForecastModel hourForecastModel = hourForecastMapper.mapToModel(hourExternalDto);
        return hourForecastModel;
    }

    private WeatherFetchEntity getWeatherFetchEntity(WeatherFetchModel weatherFetchModel) {
        WeatherFetchEntity weatherFetchEntity = weatherFetchEntityMapper.mapToEntity(weatherFetchModel);
        for (WeatherFetchLocationModel weatherFetchLocationModel : weatherFetchModel.getWeatherFetchLocations()) {
            weatherFetchEntity.addWeatherFetchLocation(getWeatherFetchLocationEntity(weatherFetchLocationModel));
        }
        return weatherFetchEntity;
    }

    private WeatherFetchLocationEntity getWeatherFetchLocationEntity(WeatherFetchLocationModel weatherFetchLocationModel) {
        WeatherFetchLocationEntity weatherFetchLocationEntity = weatherFetchLocationEntityMapper.mapToEntity(weatherFetchLocationModel);
        for (ForecastDayModel forecastday : weatherFetchLocationModel.getForecastDays()){
            weatherFetchLocationEntity.addForecastDay(getForecastDayEntity(forecastday));
        }
        return weatherFetchLocationEntity;
    }

    private ForecastDayEntity getForecastDayEntity(ForecastDayModel forecastDayModel) {
        ForecastDayEntity forecastDayEntity = forecastDayEntityMapper.mapToEntity(forecastDayModel);
        for (HourForecastModel hourForecast : forecastDayModel.getHourForecasts()) {
            forecastDayEntity.addHourForecast(getHourForecastEntity(hourForecast));
        }
        return forecastDayEntity;
    }

    private HourForecastEntity getHourForecastEntity(HourForecastModel hourForecast) {
        HourForecastEntity hourForecastEntity = hourForecastEntityMapper.mapToEntity(hourForecast);
        return hourForecastEntity;
    }
}
