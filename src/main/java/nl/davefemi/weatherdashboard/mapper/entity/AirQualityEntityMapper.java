package nl.davefemi.weatherdashboard.mapper.entity;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.database.entity.AirQualityEntity;
import nl.davefemi.weatherdashboard.database.entity.RealtimeWeatherEntity;
import nl.davefemi.weatherdashboard.domain.model.AirQualityModel;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AirQualityEntityMapper {
    RealtimeWeatherEntityMapper realtimeWeatherEntityMapper;

    public AirQualityEntity mapToEntity(AirQualityModel airQualityModel) {
        AirQualityEntity entity = new AirQualityEntity();
        RealtimeWeatherEntity realtimeWeather = realtimeWeatherEntityMapper.mapToEntity(airQualityModel.getRealtimeWeather());
        entity.setRealtimeWeatherId(realtimeWeather.getId());
        entity.setRealtimeWeather(realtimeWeather);
        entity.setCo(airQualityModel.getCo());
        entity.setNo2(airQualityModel.getNo2());
        entity.setO3(airQualityModel.getO3());
        entity.setSo2(airQualityModel.getSo2());
        entity.setPm2_5(airQualityModel.getPm2_5());
        entity.setPm10(airQualityModel.getPm10());
        entity.setUs_epa_index(airQualityModel.getUs_epa_index());
        entity.setGb_defra_index(airQualityModel.getGb_defra_index());
        return entity;
    }
}
