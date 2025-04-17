package nl.davefemi.weatherdashboard.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.client.CurrentWeatherClient;
import nl.davefemi.weatherdashboard.client.ForecastWeatherClient;
import nl.davefemi.weatherdashboard.database.entity.ForecastDayEntity;
import nl.davefemi.weatherdashboard.database.entity.HourForecastEntity;
import nl.davefemi.weatherdashboard.database.entity.WeatherFetchEntity;
import nl.davefemi.weatherdashboard.domain.model.*;
import nl.davefemi.weatherdashboard.domain.service.registry.ApiClientDescription;
import nl.davefemi.weatherdashboard.domain.service.registry.LocationDescription;
import nl.davefemi.weatherdashboard.database.repository.*;
import nl.davefemi.weatherdashboard.domain.service.registry.WeatherConditionRegistry;
import nl.davefemi.weatherdashboard.dto.external.CurrentWeatherExternalDto;
import nl.davefemi.weatherdashboard.dto.external.ForecastWeatherExternalDto;
import nl.davefemi.weatherdashboard.dto.external.component.ConditionExternalDto;
import nl.davefemi.weatherdashboard.dto.external.component.ForecastdayExternalDto;
import nl.davefemi.weatherdashboard.dto.external.component.HourExternalDto;
import nl.davefemi.weatherdashboard.mapper.CurrentWeatherMapper;
import nl.davefemi.weatherdashboard.mapper.domain.*;
import nl.davefemi.weatherdashboard.mapper.entity.ForecastDayEntityMapper;
import nl.davefemi.weatherdashboard.mapper.entity.HourForecastEntityMapper;
import nl.davefemi.weatherdashboard.mapper.entity.WeatherFetchEntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ForecastDataUpdateService {
    private final ForecastWeatherClient forecastWeatherClient;
    private final CurrentWeatherClient currentWeatherClient;
    private final CurrentWeatherMapper currentWeatherMapper;
    private final CurrentWeatherRepository currentWeatherRepository;
    private final WeatherFetchMapper weatherFetchMapper;
    private final ApiClientMapper apiClientMapper;
    private final LocationMapper locationMapper;
    private final RealtimeWeatherMapper realtimeWeatherMapper;
    private final AirQualityMapper airQualityMapper;
    private final WeatherConditionMapper weatherConditionMapper;
    private final DailyForecastMapper dailyForecastMapper;
    private final ForecastDayMapper forecastDayMapper;
    private final AstroMapper astroMapper;
    private final HourForecastMapper hourForecastMapper;
    private final WeatherFetchEntityMapper weatherFetchEntityMapper;
    private final HourForecastEntityMapper hourForecastEntityMapper;
    private final WeatherFetchRepository weatherFetchRepository;
    private final RealtimeWeatherRepository realtimeWeatherRepository;
    private final AirQualityRepository airQualityRepository;
    private final WeatherConditionRepository weatherConditionRepository;
    private final ForecastDayRepository forecastDayRepository;
    private final DailyForecastRepository dailyForecastRepository;
    private final AstroRepository astroRepository;
    private final HourForecastRepository hourForecastRepository;
    private final WeatherConditionRegistry weatherConditionRegistry;
    private final ForecastDayEntityMapper forecastDayEntityMapper;

    public void updateCurrentWeatherData(String location) {
        CurrentWeatherExternalDto dto = currentWeatherClient.getExternalDto(location);
        currentWeatherRepository
                .save(currentWeatherMapper
                        .mapToCurrentWeatherEntity(currentWeatherMapper
                                .mapToCurrentWeather(dto)));
    }

    public void updateForecastData(ApiClientDescription apiClient, LocationDescription location) {
        WeatherFetchModel model = getWeatherFetchModel(forecastWeatherClient.getExternalDto(location.getLocation().getName()), apiClient.getApiClientModel(), location.getLocation());
//        log.info("Expose content here {}", model.toString());

        WeatherFetchEntity weatherFetchEntity = weatherFetchEntityMapper.mapToEntity(model);
            for (ForecastDayModel forecastdayModel : model.getForecastdays()) {
                ForecastDayEntity forecastDayEntity = forecastDayEntityMapper.mapToEntity(forecastdayModel);
                for (HourForecastModel hourForecast : forecastdayModel.getHourForecasts()) {
                    HourForecastEntity hourForecastEntity = hourForecastEntityMapper.mapToEntity(hourForecast);
                    hourForecastEntity.setForecastDay(forecastDayEntity);
                    forecastDayEntity.getHourForecasts().add(hourForecastEntity);
                }
                forecastDayEntity.getDailyForecast().setForecastDay(forecastDayEntity);
                forecastDayEntity.getAstro().setForecastDay(forecastDayEntity);
                forecastDayEntity.setWeatherFetch(weatherFetchEntity);
                weatherFetchEntity.getForecastdays().add(forecastDayEntity);
        }
            weatherFetchRepository.save(weatherFetchEntity);
    }

    private WeatherFetchModel getWeatherFetchModel(ForecastWeatherExternalDto forecastWeatherExternalDto, ApiClientModel apiClientModel, LocationModel locationModel) {
        WeatherFetchModel weatherFetchModel = weatherFetchMapper.mapToModel(forecastWeatherExternalDto, apiClientModel, locationModel);
        WeatherConditionModel realtimeWeatherConditionModel =
                getWeatherConditionModel(forecastWeatherExternalDto.getCurrent().getCondition());
        RealtimeWeatherModel realtimeWeatherModel = realtimeWeatherMapper.mapToModel(
                forecastWeatherExternalDto.getCurrent(), weatherFetchModel, realtimeWeatherConditionModel);
        addForecastDays(weatherFetchModel, forecastWeatherExternalDto.getForecast().getForecastday());
        return weatherFetchModel;
    }

    private WeatherConditionModel getWeatherConditionModel(ConditionExternalDto weatherConditionExternalDto){
        WeatherConditionModel weatherConditionModel;
        if (weatherConditionRegistry.containsWeatherCondition(weatherConditionExternalDto.getCode())){
            weatherConditionModel = weatherConditionRegistry.getWeatherCondition(weatherConditionExternalDto.getCode());
        }
        else {
            weatherConditionModel = weatherConditionMapper.mapToModel(weatherConditionExternalDto);
            weatherConditionRegistry.registerWeatherCondition(weatherConditionModel);
        }
        return weatherConditionModel;
    }

    private void addForecastDays(WeatherFetchModel weatherFetchModel, List<ForecastdayExternalDto> forecastDays) {
        for (ForecastdayExternalDto forecastDayExternalDto : forecastDays) {
            WeatherConditionModel dailyWeatherConditionModel =
                    getWeatherConditionModel(forecastDayExternalDto.getDay().getCondition());
            DailyForecastModel dailyForecastModel = dailyForecastMapper.mapToModel(
                    forecastDayExternalDto.getDay(),
                    dailyWeatherConditionModel);
            ForecastDayModel forecastdayModel =
                    forecastDayMapper.mapToModel(forecastDayExternalDto,
                            astroMapper.mapToModel(forecastDayExternalDto.getAstro()),
                            dailyForecastModel);
            for (HourExternalDto hourExternalDto : forecastDayExternalDto.getHour()) {
                WeatherConditionModel hourWeatherConditionModel =
                        getWeatherConditionModel(hourExternalDto.getCondition());
                HourForecastModel hourForecastModel = hourForecastMapper.mapToModel(
                        hourExternalDto, forecastdayModel, hourWeatherConditionModel);
                forecastdayModel.getHourForecasts().add(hourForecastModel);
            }
            weatherFetchModel.getForecastdays().add(forecastdayModel);
        }
    }

}
