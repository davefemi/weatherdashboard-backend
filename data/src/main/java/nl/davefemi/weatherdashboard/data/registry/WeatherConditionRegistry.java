package nl.davefemi.weatherdashboard.data.registry;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.data.entity.WeatherConditionEntity;
import nl.davefemi.weatherdashboard.data.repository.WeatherConditionRepository;
import nl.davefemi.weatherdashboard.data.mapper.domain.WeatherConditionMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class WeatherConditionRegistry {
    private final WeatherConditionMapper weatherConditionMapper;
    private final WeatherConditionRepository weatherConditionRepository;
    private final Map<Long, WeatherConditionEntity> weatherConditions = new HashMap<>();

    @PostConstruct
    public void init() {
        weatherConditionRepository.findAll().forEach(weatherCondition -> {
            weatherConditions.put(weatherCondition.getCode(), weatherCondition);
        });
    }

    public boolean containsWeatherCondition(Long code) {
        return weatherConditions.containsKey(code);
    }

    public WeatherConditionEntity getWeatherCondition(Long code) {
        return weatherConditions.get(code);
    }

    public void registerWeatherCondition(WeatherConditionEntity weatherConditionEntity) {
        weatherConditions.put(weatherConditionEntity.getCode(), weatherConditionEntity);
    }

}
