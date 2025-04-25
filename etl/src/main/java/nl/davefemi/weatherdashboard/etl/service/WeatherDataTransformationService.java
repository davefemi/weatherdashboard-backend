package nl.davefemi.weatherdashboard.etl.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.client.dto.ErrorExternalDto;
import nl.davefemi.weatherdashboard.client.dto.ExternalDto;
import nl.davefemi.weatherdashboard.client.dto.ExternalDtoAggregator;
import nl.davefemi.weatherdashboard.client.dto.ForecastWeatherExternalDto;
import nl.davefemi.weatherdashboard.client.dto.component.CurrentExternalDto;
import nl.davefemi.weatherdashboard.client.dto.component.ForecastdayExternalDto;
import nl.davefemi.weatherdashboard.client.dto.component.HourExternalDto;
import nl.davefemi.weatherdashboard.data.mapper.domain.*;
import nl.davefemi.weatherdashboard.data.mapper.entity.ForecastDayEntityMapper;
import nl.davefemi.weatherdashboard.data.mapper.entity.HourForecastEntityMapper;
import nl.davefemi.weatherdashboard.data.mapper.entity.WeatherFetchEntityMapper;
import nl.davefemi.weatherdashboard.data.mapper.entity.WeatherFetchLocationEntityMapper;
import nl.davefemi.weatherdashboard.data.model.*;
import nl.davefemi.weatherdashboard.data.registry.LocationRegistry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class WeatherDataTransformationService {
    private final WeatherFetchMapper weatherFetchMapper;
    private final WeatherFetchLocationMapper weatherFetchLocationMapper;
    private final RealtimeWeatherMapper realtimeWeatherMapper;
    private final ErrorLogMapper errorLogMapper;
    private final JsonRawDataMapper jsonRawDataMapper;
    private final ForecastDayMapper forecastDayMapper;
    private final HourForecastMapper hourForecastMapper;
    private final LocationRegistry locationRegistry;

    public WeatherFetchModel getWeatherFetchModel(ExternalDtoAggregator externalDtoAggregator, ApiClientModel apiClientModel) {
        WeatherFetchModel weatherFetchModel = weatherFetchMapper.mapToModel(apiClientModel);
        List<WeatherFetchLocationModel> weatherFetchLocationModels = getWeatherFetchLocationModels(externalDtoAggregator);
        for (WeatherFetchLocationModel weatherFetchLocationModel : weatherFetchLocationModels) {
            weatherFetchModel.addWeatherFetchLocation(weatherFetchLocationModel);
        }
        return weatherFetchModel;
    }

    private List<WeatherFetchLocationModel> getWeatherFetchLocationModels (ExternalDtoAggregator externalDtoAggregator){
        List<WeatherFetchLocationModel> weatherFetchLocationModels = new ArrayList<>();
        for (Map.Entry <String, ExternalDto> entry : externalDtoAggregator.getExternalDto().entrySet()){
            if (entry.getValue() instanceof ForecastWeatherExternalDto){
                ForecastWeatherExternalDto forecastWeatherExternalDto = (ForecastWeatherExternalDto) entry.getValue();
                String location = entry.getKey();
                WeatherFetchLocationModel weatherFetchLocationModel;
                weatherFetchLocationModel =
                        weatherFetchLocationMapper.mapToModel(
                                forecastWeatherExternalDto,
                                locationRegistry.getLocationDescription(location).getLocation());
                weatherFetchLocationModel.setRealtimeWeather(getRealTimeWeatherModel(forecastWeatherExternalDto.getCurrent()));
                weatherFetchLocationModel.setJsonRawData(getJsonRawDataModel(externalDtoAggregator.getJsonRawData().get(location)));
                for (ForecastdayExternalDto forecastDayExternalDto : forecastWeatherExternalDto.getForecast().getForecastday()) {
                    weatherFetchLocationModel.addForecastDay(getForecastDayModel(forecastDayExternalDto));
                }
                weatherFetchLocationModels.add(weatherFetchLocationModel);
            }
            else {
                WeatherFetchLocationModel weatherFetchLocationModel =
                        getErrorWeatherModel((ErrorExternalDto) entry.getValue(), entry.getKey());
                weatherFetchLocationModels.add(weatherFetchLocationModel);
            }
        }
        return weatherFetchLocationModels;
    }

    private WeatherFetchLocationModel getErrorWeatherModel(ErrorExternalDto errorExternalDto, String location) {
        ErrorLogModel errorLogModel = errorLogMapper.mapToModel(errorExternalDto);
        WeatherFetchLocationModel weatherFetchLocationModel =
                weatherFetchLocationMapper.MapForErrorModel(
                        locationRegistry.getLocationDescription(location).getLocation(), errorLogModel);
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
}
