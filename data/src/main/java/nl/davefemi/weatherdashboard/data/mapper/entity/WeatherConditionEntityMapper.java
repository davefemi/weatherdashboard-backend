package nl.davefemi.weatherdashboard.data.mapper.entity;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.data.entity.WeatherConditionEntity;
import nl.davefemi.weatherdashboard.data.repository.WeatherConditionRepository;
import nl.davefemi.weatherdashboard.data.model.WeatherConditionModel;
import nl.davefemi.weatherdashboard.data.registry.WeatherConditionRegistry;
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
