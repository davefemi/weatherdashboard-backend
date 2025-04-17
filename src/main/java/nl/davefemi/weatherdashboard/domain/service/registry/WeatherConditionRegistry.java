package nl.davefemi.weatherdashboard.domain.service.registry;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.database.repository.WeatherConditionRepository;
import nl.davefemi.weatherdashboard.domain.model.WeatherConditionModel;
import nl.davefemi.weatherdashboard.mapper.domain.WeatherConditionMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class WeatherConditionRegistry {
    private final WeatherConditionMapper weatherConditionMapper;
    private final WeatherConditionRepository weatherConditionRepository;
    private final Map<Long, WeatherConditionModel> weatherConditions = new HashMap<>();

    @PostConstruct
    public void init() {
        weatherConditionRepository.findAll().forEach(weatherCondition -> {
            WeatherConditionModel model = weatherConditionMapper.mapToModel(weatherCondition);
            weatherConditions.put(model.getCode(), model);
        });
    }

    public boolean containsWeatherCondition(Long code) {
        return weatherConditions.containsKey(code);
    }

    public WeatherConditionModel getWeatherCondition(Long code) {
        return weatherConditions.get(code);
    }

    public void registerWeatherCondition(WeatherConditionModel weatherConditionModel) {
        weatherConditions.put(weatherConditionModel.getCode(), weatherConditionModel);
    }

}
