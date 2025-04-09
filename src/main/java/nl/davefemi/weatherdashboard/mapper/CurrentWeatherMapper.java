package nl.davefemi.weatherdashboard.mapper;

import nl.davefemi.weatherdashboard.database.entity.CurrentWeatherEntity;
import nl.davefemi.weatherdashboard.domain.model.CurrentWeather;
import nl.davefemi.weatherdashboard.dto.external.CurrentWeatherExternalDto;
import nl.davefemi.weatherdashboard.dto.response.CurrentWeatherResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CurrentWeatherMapper {

    public CurrentWeather mapToCurrentWeather(CurrentWeatherExternalDto externalDto){
        CurrentWeather domain = new CurrentWeather();
        domain.setCity(externalDto.getLocation().getCity());
        domain.setRegion(externalDto.getLocation().getRegion());
        domain.setCountry(externalDto.getLocation().getCountry());
        domain.setTemperature(externalDto.getCurrent().getTemperature_C());
        return domain;
    }

    public CurrentWeatherEntity mapToCurrentWeatherEntity(CurrentWeather domain){
        CurrentWeatherEntity entity = new CurrentWeatherEntity();
        entity.setCity(domain.getCity());
        entity.setRegion(domain.getRegion());
        entity.setCountry(domain.getCountry());
        entity.setTemperatureC(domain.getTemperature());
        return entity;
    }

    public CurrentWeatherResponseDto mapToCurrentWeatherResponseDto(CurrentWeather domain){
        CurrentWeatherResponseDto dto = new CurrentWeatherResponseDto();
        return dto;
    }
}
