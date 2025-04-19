package nl.davefemi.weatherdashboard.etl.domain.service.registry;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.etl.database.repository.LocationRepository;
import nl.davefemi.weatherdashboard.etl.domain.model.LocationModel;
import nl.davefemi.weatherdashboard.etl.mapper.domain.LocationMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class LocationRegistry {
    private Map<String, LocationDescription> locations = new HashMap<>();
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @PostConstruct
    public void init() {
        locationRepository.findAll().forEach(location -> {
            LocationModel model = locationMapper.mapToModel(location);
            locations.put(model.getName(), new LocationDescription(model, model.getContinent()));
        });
    }

    public LocationDescription getLocationDescprition(String location){
        return locations.get(location);
    }

    public Map<String, LocationDescription> getLocations() {
        return locations;
    }
}
