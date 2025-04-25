package nl.davefemi.weatherdashboard.etl.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.client.api.ApiResponse;
import nl.davefemi.weatherdashboard.client.dto.ErrorExternalDto;
import nl.davefemi.weatherdashboard.client.dto.ExternalDto;
import nl.davefemi.weatherdashboard.client.dto.ExternalDtoAggregator;
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

import java.util.ArrayList;
import java.util.List;
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
    private final LocationRegistry locationRegistry;
    private final ApiClientRegistry apiClientRegistry;
    private final WeatherDataTransformationService weatherDataTransformationService;
    private final WeatherDataPersistenceService weatherDataPersistenceService;

    /**
     * This is the main method which calls helper methods. It will obtain the high level domain model
     * and send it for transformation to an entity which will be persisted.
     */
    @Transactional
    public void updateForecastWeatherData() {
        List<String> locations = new ArrayList<>();
        for (LocationDescription locationDescription : locationRegistry.getLocations().values()) {
            locations.add(locationDescription.getLocation().getName());
        }
        ExternalDtoAggregator externalDtoAggregator = forecastWeatherClient.getExternalDtoAggregator(locations);
        WeatherFetchModel weatherFetchModel = weatherDataTransformationService.getWeatherFetchModel(
                externalDtoAggregator,
                apiClientRegistry.getApiClientDescription(forecastWeatherClient).getApiClientModel());
        weatherDataPersistenceService.persistWeatherFetchModel(weatherFetchModel);
    }
}
