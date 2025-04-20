package nl.davefemi.weatherdashboard.etl.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.data.model.*;
import nl.davefemi.weatherdashboard.client.api.ForecastWeatherClient;
import nl.davefemi.weatherdashboard.data.entity.ForecastDayEntity;
import nl.davefemi.weatherdashboard.data.entity.HourForecastEntity;
import nl.davefemi.weatherdashboard.data.entity.WeatherFetchEntity;
import nl.davefemi.weatherdashboard.data.entity.WeatherFetchLocationEntity;
import nl.davefemi.weatherdashboard.data.repository.WeatherFetchRepository;
import nl.davefemi.weatherdashboard.data.mapper.domain.*;
import nl.davefemi.weatherdashboard.etl.service.registry.ApiClientDescription;
import nl.davefemi.weatherdashboard.etl.service.registry.ApiClientRegistry;
import nl.davefemi.weatherdashboard.data.registry.LocationDescription;
import nl.davefemi.weatherdashboard.data.registry.LocationRegistry;
import nl.davefemi.weatherdashboard.client.dto.ForecastWeatherExternalDto;
import nl.davefemi.weatherdashboard.client.dto.component.CurrentExternalDto;
import nl.davefemi.weatherdashboard.client.dto.component.ForecastdayExternalDto;
import nl.davefemi.weatherdashboard.client.dto.component.HourExternalDto;
import nl.davefemi.weatherdashboard.data.mapper.entity.ForecastDayEntityMapper;
import nl.davefemi.weatherdashboard.data.mapper.entity.HourForecastEntityMapper;
import nl.davefemi.weatherdashboard.data.mapper.entity.WeatherFetchEntityMapper;
import nl.davefemi.weatherdashboard.data.mapper.entity.WeatherFetchLocationEntityMapper;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * Class responsible for requesting data from client class and orchestration of
 * the persistence flow
 */
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
    private final JsonRawDataMapper jsonRawDataMapper;
    private final WeatherFetchRepository weatherFetchRepository;
    private final ForecastDayEntityMapper forecastDayEntityMapper;
    private final LocationRegistry locationRegistry;
    private final ApiClientRegistry apiClientRegistry;
    private final ObjectMapper objectMapper;

    /**
     * This is the main method which calls helper methods. It will obtain the high level domain model
     * and send it for transformation to an entity which will be persisted.
     */
    @Transactional
    public void updateForecastWeatherData() {
        WeatherFetchModel weatherFetchModel = getWeatherFetchModel();
        WeatherFetchEntity weatherFetchEntity = getWeatherFetchEntity(weatherFetchModel);
        weatherFetchRepository.save(weatherFetchEntity);
    }

    /**
     * Helper method obtaining the WeatherFetchModel which is an aggregate of fetches for different predetermined
     * locations. It will make use of the local location and api registry to be able to correctly assign each fetch to
     * the api and location
     * @return WeatherFetchModel with a list of WeatherFetchLocations
     */
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

    /**
     * Helper method obtaining the individual locational fetches and transforming them into domain models. Also responsible
     * for passing the raw data into an individual model for persistence
     * @param location of the fetch
     * @return WeatherFetchModel with its attributes
     */
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
                        locationRegistry.getLocationDescprition(location).getLocation());
        weatherFetchLocationModel.setRealtimeWeather(getRealTimeWeatherModel(forecastWeatherExternalDto.getCurrent()));
        weatherFetchLocationModel.setJsonRawData(getJsonRawDataModel(rawJsonData));
        for (ForecastdayExternalDto forecastDayExternalDto : forecastWeatherExternalDto.getForecast().getForecastday())
            weatherFetchLocationModel.addForecastDay(getForecastDayModel(forecastDayExternalDto));
        return weatherFetchLocationModel;
    }

    /**
     * Responsible for obtaining a domain model with realtime weather data
     * @param currentExternalDto data from Api call mapped into a respresentational strucutre
     * @return RealTimeWeatherModel
     */
    private RealtimeWeatherModel getRealTimeWeatherModel(CurrentExternalDto currentExternalDto) {
        return realtimeWeatherMapper.mapToModel(currentExternalDto);
    }

    /**
     * Responsible for obtaining a domain model with raw data
     * @param rawJsonData JsonNode from Api call
     * @return JsonRawDataModel
     */
    private JsonRawDataModel getJsonRawDataModel(JsonNode rawJsonData) {
        return jsonRawDataMapper.mapToModel(rawJsonData);
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
