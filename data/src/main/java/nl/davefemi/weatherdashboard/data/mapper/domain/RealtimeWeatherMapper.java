package nl.davefemi.weatherdashboard.data.mapper.domain;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.data.entity.RealtimeWeatherEntity;
import nl.davefemi.weatherdashboard.data.model.RealtimeWeatherModel;
import nl.davefemi.weatherdashboard.client.dto.component.CurrentExternalDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RealtimeWeatherMapper {
    private final WeatherConditionMapper weatherConditionMapper;
    private final AirQualityMapper airQualityMapper;


    public RealtimeWeatherModel mapToModel(
            CurrentExternalDto currentWeatherExternalDto) {
        RealtimeWeatherModel model = new RealtimeWeatherModel();
        model.setLastUpdatedEpoch(currentWeatherExternalDto.getLast_updated_epoch());
        model.setTemperatureC(currentWeatherExternalDto.getTemp_c());
        model.setCondition(weatherConditionMapper.mapToModel(currentWeatherExternalDto.getCondition()));
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
        model.setUv(currentWeatherExternalDto.getUv());
        model.setAirQuality(airQualityMapper.mapToModel(currentWeatherExternalDto.getAir_quality()));
        return model;
    }

    public RealtimeWeatherModel mapToModel(RealtimeWeatherEntity realtimeWeatherEntity) {
        RealtimeWeatherModel model = new RealtimeWeatherModel();
        model.setWeatherFetchLocationId(realtimeWeatherEntity.getId());
//        model.setWeatherFetchLocation(weatherFetchLocationMapper.mapToModel(realtimeWeatherEntity.getWeatherFetch()));
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
