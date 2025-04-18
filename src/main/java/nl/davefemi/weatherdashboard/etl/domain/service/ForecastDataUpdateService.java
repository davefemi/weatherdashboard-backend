package nl.davefemi.weatherdashboard.etl.domain.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.etl.client.ForecastWeatherClient;
import nl.davefemi.weatherdashboard.etl.database.entity.ForecastDayEntity;
import nl.davefemi.weatherdashboard.etl.database.entity.HourForecastEntity;
import nl.davefemi.weatherdashboard.etl.database.entity.WeatherFetchEntity;
import nl.davefemi.weatherdashboard.etl.database.entity.WeatherFetchLocationEntity;
import nl.davefemi.weatherdashboard.etl.database.repository.WeatherFetchRepository;
import nl.davefemi.weatherdashboard.etl.domain.model.*;
import nl.davefemi.weatherdashboard.etl.domain.service.registry.ApiClientDescription;
import nl.davefemi.weatherdashboard.etl.domain.service.registry.ApiClientRegistry;
import nl.davefemi.weatherdashboard.etl.domain.service.registry.LocationDescription;
import nl.davefemi.weatherdashboard.etl.domain.service.registry.LocationRegistry;
import nl.davefemi.weatherdashboard.etl.dto.external.ForecastWeatherExternalDto;
import nl.davefemi.weatherdashboard.etl.dto.external.component.CurrentExternalDto;
import nl.davefemi.weatherdashboard.etl.dto.external.component.ForecastdayExternalDto;
import nl.davefemi.weatherdashboard.etl.dto.external.component.HourExternalDto;
import nl.davefemi.weatherdashboard.etl.mapper.domain.*;
import nl.davefemi.weatherdashboard.etl.mapper.entity.ForecastDayEntityMapper;
import nl.davefemi.weatherdashboard.etl.mapper.entity.HourForecastEntityMapper;
import nl.davefemi.weatherdashboard.etl.mapper.entity.WeatherFetchEntityMapper;
import nl.davefemi.weatherdashboard.etl.mapper.entity.WeatherFetchLocationEntityMapper;
import org.springframework.stereotype.Service;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class ForecastDataUpdateService {
    private final ForecastWeatherClient forecastWeatherClient;
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

    @Transactional
    public void updateForecastWeatherData() {
        WeatherFetchModel weatherFetchModel = getWeatherFetchModel();
        WeatherFetchEntity weatherFetchEntity = getWeatherFetchEntity(weatherFetchModel);
        weatherFetchRepository.save(weatherFetchEntity);
    }

    private WeatherFetchModel getWeatherFetchModel() {
        Map<String, LocationDescription> locationDescriptions = locationRegistry.getLocations();
        ApiClientDescription apiClientDescription = apiClientRegistry.getApiClientDescription(forecastWeatherClient);
        WeatherFetchModel weatherFetchModel = weatherFetchMapper.mapToModel(apiClientDescription.getApiClientModel());
        for (String location : locationDescriptions.keySet()) {
            WeatherFetchLocationModel weatherFetchLocationModel = getWeatherFetchLocationModel(location);
            weatherFetchModel.addWeatherFetchLocation(weatherFetchLocationModel);
        }
        return weatherFetchModel;
    }

    private WeatherFetchLocationModel getWeatherFetchLocationModel(String location) {
        String fetchResponse = forecastWeatherClient.getResponseJson(location);
        ForecastWeatherExternalDto forecastWeatherExternalDto = forecastWeatherClient.getExternalDto(fetchResponse);
        WeatherFetchLocationModel weatherFetchLocationModel;
        JsonNode rawJsonData;
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
        weatherFetchLocationModel.setRealtimeWeather(getRealTimeWeatherModel(forecastWeatherExternalDto.getCurrent()));
        for (ForecastdayExternalDto forecastDayExternalDto : forecastWeatherExternalDto.getForecast().getForecastday())
            weatherFetchLocationModel.addForecastDay(getForecastDayModel(forecastDayExternalDto));
        return weatherFetchLocationModel;
    }

    private RealtimeWeatherModel getRealTimeWeatherModel(CurrentExternalDto currentExternalDto) {
        return realtimeWeatherMapper.mapToModel(currentExternalDto);
    }

    private ForecastDayModel getForecastDayModel(ForecastdayExternalDto forecastdayExternalDto) {
        ForecastDayModel forecastDayModel = forecastDayMapper.mapToModel(forecastdayExternalDto);
        for (HourExternalDto hourExternalDto : forecastdayExternalDto.getHour()) {
            forecastDayModel.addHourForecast(getHourForecastModel(hourExternalDto));
        }
        return forecastDayModel;
    }

    private HourForecastModel getHourForecastModel(HourExternalDto hourExternalDto) {
        return hourForecastMapper.mapToModel(hourExternalDto);
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
        return hourForecastEntityMapper.mapToEntity(hourForecast);
    }
}
