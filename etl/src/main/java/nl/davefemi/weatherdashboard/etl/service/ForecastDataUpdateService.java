package nl.davefemi.weatherdashboard.etl.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.client.dto.weatherapi.ExternalDtoAggregator;
import nl.davefemi.weatherdashboard.data.model.*;
import nl.davefemi.weatherdashboard.client.api.client.ForecastWeatherClient;
import nl.davefemi.weatherdashboard.etl.service.registry.ApiClientRegistry;
import nl.davefemi.weatherdashboard.data.registry.LocationDescription;
import nl.davefemi.weatherdashboard.data.registry.LocationRegistry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
