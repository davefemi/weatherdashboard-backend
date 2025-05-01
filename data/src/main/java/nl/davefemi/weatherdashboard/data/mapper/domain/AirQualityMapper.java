package nl.davefemi.weatherdashboard.data.mapper.domain;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.data.entity.AirQualityEntity;
import nl.davefemi.weatherdashboard.data.model.AirQualityModel;
import nl.davefemi.weatherdashboard.client.dto.weatherapi.component.AirQualityExternalDto;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class AirQualityMapper {
    public AirQualityModel mapToModel(AirQualityExternalDto airQualityExternalDto) {
        AirQualityModel model = new AirQualityModel();
        model.setCo(airQualityExternalDto.getCo());
        model.setNo2(airQualityExternalDto.getNo2());
        model.setO3(airQualityExternalDto.getO3());
        model.setSo2(airQualityExternalDto.getSo2());
        model.setPm2_5(airQualityExternalDto.getPm2_5());
        model.setPm10(airQualityExternalDto.getPm10());
        model.setUs_epa_index(airQualityExternalDto.getUs_epa_index());
        model.setGb_defra_index(airQualityExternalDto.getGb_defra_index());
        return model;
    }



    public AirQualityModel mapToModel(AirQualityEntity airQualityEntity) {
        AirQualityModel model = new AirQualityModel();
        model.setRealtimeWeatherId(airQualityEntity.getRealtimeWeatherId());
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
