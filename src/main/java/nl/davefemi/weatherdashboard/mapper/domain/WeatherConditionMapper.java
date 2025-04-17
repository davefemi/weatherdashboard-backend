package nl.davefemi.weatherdashboard.mapper.domain;

import nl.davefemi.weatherdashboard.database.entity.WeatherConditionEntity;
import nl.davefemi.weatherdashboard.domain.model.WeatherConditionModel;
import nl.davefemi.weatherdashboard.dto.external.component.ConditionExternalDto;
import org.springframework.stereotype.Component;

@Component
public class WeatherConditionMapper {
    public WeatherConditionModel mapToModel(ConditionExternalDto weatherCondition) {
        WeatherConditionModel model = new WeatherConditionModel();
        model.setCode(weatherCondition.getCode());
        model.setText(weatherCondition.getText());
        return model;
    }

    public WeatherConditionModel mapToModel(WeatherConditionEntity weatherConditionEntity) {
        WeatherConditionModel model = new WeatherConditionModel();
        model.setCode(weatherConditionEntity.getCode());
        model.setText(weatherConditionEntity.getText());
        return model;
    }
}
