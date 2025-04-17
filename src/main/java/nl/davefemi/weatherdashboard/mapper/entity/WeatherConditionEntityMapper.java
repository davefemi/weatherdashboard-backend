package nl.davefemi.weatherdashboard.mapper.entity;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.database.entity.WeatherConditionEntity;
import nl.davefemi.weatherdashboard.database.repository.WeatherConditionRepository;
import nl.davefemi.weatherdashboard.domain.model.WeatherConditionModel;
import nl.davefemi.weatherdashboard.domain.service.registry.WeatherConditionRegistry;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WeatherConditionEntityMapper {
    private final WeatherConditionRegistry weatherConditionRegistry;
    private final WeatherConditionRepository weatherConditionRepository;

    public WeatherConditionEntity mapToEntity(WeatherConditionModel weatherConditionModel) {
        if (weatherConditionRegistry.containsWeatherCondition(weatherConditionModel.getCode())) {
            return weatherConditionRegistry.getWeatherCondition(weatherConditionModel.getCode());
        }
        else {
            WeatherConditionEntity entity = new WeatherConditionEntity();
            entity.setCode(weatherConditionModel.getCode());
            entity.setText(weatherConditionModel.getText());
            weatherConditionRegistry.registerWeatherCondition(weatherConditionRepository.save(entity));
            return weatherConditionRegistry.getWeatherCondition(entity.getCode());
        }
    }
}
