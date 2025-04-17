package nl.davefemi.weatherdashboard.mapper.domain;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.database.entity.RealtimeWeatherEntity;
import nl.davefemi.weatherdashboard.domain.model.RealtimeWeatherModel;
import nl.davefemi.weatherdashboard.domain.model.WeatherConditionModel;
import nl.davefemi.weatherdashboard.domain.model.WeatherFetchModel;
import nl.davefemi.weatherdashboard.dto.external.ForecastWeatherExternalDto;
import nl.davefemi.weatherdashboard.dto.external.component.CurrentExternalDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RealtimeWeatherMapper {
    private final WeatherConditionMapper weatherConditionMapper;
    private final WeatherFetchMapper weatherFetchMapper;

    public RealtimeWeatherModel mapToModel(
            CurrentExternalDto currentWeatherExternalDto,
            WeatherFetchModel weatherFetchModel, WeatherConditionModel weatherConditionModel) {
        RealtimeWeatherModel model = new RealtimeWeatherModel();
        model.setWeatherFetch(weatherFetchModel);
        model.setLastUpdatedEpoch(currentWeatherExternalDto.getLast_updated_epoch());
        model.setTemperatureC(currentWeatherExternalDto.getTemp_c());
        model.setCondition(weatherConditionModel);
        model.setDay(currentWeatherExternalDto.getIs_day()==1);
        model.setWindDegree(currentWeatherExternalDto.getWind_degree());
        model.setWindDirection(currentWeatherExternalDto.getWind_dir());
        model.setPressureMb(currentWeatherExternalDto.getPressure_mb());
        model.setPrecipitationMm(currentWeatherExternalDto.getPrecip_mm());
        model.setHumidity(currentWeatherExternalDto.getHumidity());
        model.setCloud(currentWeatherExternalDto.getCloud());
        model.setFeelslikeC(currentWeatherExternalDto.getFeelslike_c());
        model.setWindchillC(currentWeatherExternalDto.getWindchill_c());
        model.setHeatindexC(currentWeatherExternalDto.getHeatindex_c());
        model.setVisibilityKm(currentWeatherExternalDto.getVis_km());
        model.setGustKph(currentWeatherExternalDto.getGust_kph());
        return model;
    }

    public RealtimeWeatherModel mapToModel(RealtimeWeatherEntity realtimeWeatherEntity) {
        RealtimeWeatherModel model = new RealtimeWeatherModel();
        model.setId(realtimeWeatherEntity.getId());
        model.setWeatherFetch(weatherFetchMapper.mapToModel(realtimeWeatherEntity.getWeatherFetch()));
        model.setLastUpdatedEpoch(realtimeWeatherEntity.getLastUpdatedEpoch());
        model.setTemperatureC(realtimeWeatherEntity.getTemperatureC());
        model.setCondition(weatherConditionMapper.mapToModel(realtimeWeatherEntity.getCondition()));
        model.setDay(realtimeWeatherEntity.getIsDay());
        model.setWindKph(realtimeWeatherEntity.getWindKph());
        model.setWindDegree(realtimeWeatherEntity.getWindDegree());
        model.setWindDirection(realtimeWeatherEntity.getWindDirection());
        model.setPressureMb(realtimeWeatherEntity.getPressureMb());
        model.setPrecipitationMm(realtimeWeatherEntity.getPrecipitationMm());
        model.setHumidity(realtimeWeatherEntity.getHumidity());
        model.setCloud(realtimeWeatherEntity.getCloud());
        model.setFeelslikeC(realtimeWeatherEntity.getFeelslikeC());
        model.setWindchillC(realtimeWeatherEntity.getWindchillC());
        model.setHeatindexC(realtimeWeatherEntity.getHeatindexC());
        model.setDewpointC(realtimeWeatherEntity.getDewpointC());
        model.setVisibilityKm(realtimeWeatherEntity.getVisibilityKm());
        model.setUv(realtimeWeatherEntity.getUv());
        model.setGustKph(realtimeWeatherEntity.getGustKph());
        return model;
    }
}
