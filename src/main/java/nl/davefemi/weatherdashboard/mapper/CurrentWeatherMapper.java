package nl.davefemi.weatherdashboard.mapper;

import nl.davefemi.weatherdashboard.database.entity.CurrentWeatherEntity;
import nl.davefemi.weatherdashboard.domain.model.CurrentWeather;
import nl.davefemi.weatherdashboard.dto.external.CurrentWeatherExternalDto;
import nl.davefemi.weatherdashboard.dto.response.CurrentWeatherResponseDto;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CurrentWeatherMapper {

    public CurrentWeather mapToCurrentWeather(CurrentWeatherExternalDto externalDto){
        CurrentWeather domain = new CurrentWeather();
        domain.setFetchTimestamp(Instant.now());
        domain.setName(externalDto.getLocation().getName());
        domain.setRegion(externalDto.getLocation().getRegion());
        domain.setCountry(externalDto.getLocation().getCountry());
        domain.setTz_id(externalDto.getLocation().getTz_id());
        domain.setLocaltime(externalDto.getLocation().getLocaltime());
        domain.setTemp_c(externalDto.getCurrent().getTemp_c());
        domain.setIs_day(externalDto.getCurrent().getIs_day());
        domain.setFeelslike_c(externalDto.getCurrent().getFeelslike_c());
        domain.setCondition(externalDto.getCurrent().getCondition().getText());
        domain.setWind_kph(externalDto.getCurrent().getWind_kph());
        domain.setWind_dir(externalDto.getCurrent().getWind_dir());
        domain.setPrecip_mm(externalDto.getCurrent().getPrecip_mm());
        domain.setCloud(externalDto.getCurrent().getCloud());
        return domain;
    }

    public CurrentWeatherEntity mapToCurrentWeatherEntity(CurrentWeather domain){
        CurrentWeatherEntity entity = new CurrentWeatherEntity();
        entity.setFetchTimestamp(domain.getFetchTimestamp());
        entity.setCity(domain.getName());
        entity.setRegion(domain.getRegion());
        entity.setCountry(domain.getCountry());
        entity.setTz_id(domain.getTz_id());
        entity.setLocalTime(domain.getLocaltime());
        entity.setTemperatureC(domain.getTemp_c());
        entity.setIs_day(domain.getIs_day());
        entity.setFeelslike_c(domain.getFeelslike_c());
        entity.setCondition(domain.getCondition());
        entity.setPrecip_mm(domain.getPrecip_mm());
        entity.setCloud(domain.getCloud());
        return entity;
    }

    public CurrentWeather mapToCurrentWeather(CurrentWeatherEntity entity){
        CurrentWeather domain = new CurrentWeather();
        domain.setFetchTimestamp(Instant.now());
        domain.setName(entity.getCity());
        domain.setRegion(entity.getRegion());
        domain.setCountry(entity.getCountry());
        domain.setTz_id(entity.getTz_id());
        domain.setLocaltime(entity.getLocalTime());
        domain.setTemp_c(entity.getTemperatureC());
        domain.setFeelslike_c(entity.getFeelslike_c());
        domain.setIs_day(entity.getIs_day());
        domain.setCondition(entity.getCondition());
        domain.setCloud(entity.getCloud());
        return domain;
    }

    public CurrentWeatherResponseDto mapToCurrentWeatherResponseDto(CurrentWeather domain){
        CurrentWeatherResponseDto dto = new CurrentWeatherResponseDto();
        dto.setName(domain.getName());
        dto.setCountry(domain.getCountry());
        dto.setRegion(domain.getRegion());
        dto.setTz_id(domain.getTz_id());
        dto.setLocaltime(domain.getLocaltime());
        dto.setTemp_c(domain.getTemp_c());
        dto.setIs_day(domain.getIs_day());
        dto.setFeelslike_c(domain.getFeelslike_c());
        dto.setCondition(domain.getCondition());
        dto.setWind_kph(domain.getWind_kph());
        dto.setWind_dir(domain.getWind_dir());
        dto.setPrecip_mm(domain.getPrecip_mm());
        dto.setCloud(domain.getCloud());
        return dto;
    }
}
