package nl.davefemi.weatherdashboard.etl.mapper.entity;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.etl.database.entity.AirQualityEntity;
import nl.davefemi.weatherdashboard.etl.domain.model.AirQualityModel;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AirQualityEntityMapper {

    public AirQualityEntity mapToEntity(AirQualityModel airQualityModel) {
        AirQualityEntity entity = new AirQualityEntity();
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
