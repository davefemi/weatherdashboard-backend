package nl.davefemi.weatherdashboard.etl.mapper.domain;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.database.entity.WeatherConditionEntity;
import nl.davefemi.weatherdashboard.domain.model.WeatherConditionModel;
import nl.davefemi.weatherdashboard.etl.service.registry.WeatherConditionRegistry;
import nl.davefemi.weatherdashboard.etl.client.dto.component.ConditionExternalDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WeatherConditionMapper {
    WeatherConditionRegistry weatherConditionRegistry;

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
