package nl.davefemi.weatherdashboard.mapper.entity;

import nl.davefemi.weatherdashboard.database.entity.WeatherConditionEntity;
import nl.davefemi.weatherdashboard.domain.model.WeatherConditionModel;
import org.springframework.stereotype.Component;

@Component
public class WeatherConditionEntityMapper {
    public WeatherConditionEntity mapToEntity(WeatherConditionModel weatherConditionModel) {
        WeatherConditionEntity entity = new WeatherConditionEntity();
        entity.setCode(weatherConditionModel.getCode());
        entity.setText(weatherConditionModel.getText());
        return entity;
    }
}
