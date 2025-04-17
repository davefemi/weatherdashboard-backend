package nl.davefemi.weatherdashboard.mapper.domain;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.domain.model.RealtimeWeatherModel;
import nl.davefemi.weatherdashboard.domain.model.WeatherFetchModel;
import nl.davefemi.weatherdashboard.domain.service.registry.ApiClientDescription;
import nl.davefemi.weatherdashboard.domain.service.registry.LocationDescription;
import nl.davefemi.weatherdashboard.database.entity.AirQualityEntity;
import nl.davefemi.weatherdashboard.domain.model.AirQualityModel;
import nl.davefemi.weatherdashboard.dto.external.ForecastWeatherExternalDto;
import nl.davefemi.weatherdashboard.dto.external.component.CurrentExternalDto;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class AirQualityMapper {
    private final RealtimeWeatherMapper realtimeWeatherMapper;

    public AirQualityModel mapToModel(CurrentExternalDto currentExternalDto, RealtimeWeatherModel realtimeWeatherModel) {
        AirQualityModel model = new AirQualityModel();
        model.setRealtimeWeather(realtimeWeatherModel);
        model.setCo(currentExternalDto.getAir_quality().getCo());
        model.setNo2(currentExternalDto.getAir_quality().getNo2());
        model.setO3(currentExternalDto.getAir_quality().getO3());
        model.setSo2(currentExternalDto.getAir_quality().getSo2());
        model.setPm2_5(currentExternalDto.getAir_quality().getPm2_5());
        model.setPm10(currentExternalDto.getAir_quality().getPm10());
        model.setUs_epa_index(currentExternalDto.getAir_quality().getUs_epa_index());
        model.setGb_defra_index(currentExternalDto.getAir_quality().getGb_defra_index());
        return model;
    }



    public AirQualityModel mapToModel(AirQualityEntity airQualityEntity) {
        AirQualityModel model = new AirQualityModel();
        model.setRealtimeWeatherId(airQualityEntity.getRealtimeWeatherId());
        model.setRealtimeWeather(realtimeWeatherMapper.mapToModel(airQualityEntity.getRealtimeWeather()));
        model.setCo(airQualityEntity.getCo());
        model.setNo2(airQualityEntity.getNo2());
        model.setO3(airQualityEntity.getO3());
        model.setSo2(airQualityEntity.getSo2());
        model.setPm2_5(airQualityEntity.getPm2_5());
        model.setPm10(airQualityEntity.getPm10());
        model.setUs_epa_index(airQualityEntity.getUs_epa_index());
        model.setGb_defra_index(airQualityEntity.getGb_defra_index());
        return model;
    }
}
