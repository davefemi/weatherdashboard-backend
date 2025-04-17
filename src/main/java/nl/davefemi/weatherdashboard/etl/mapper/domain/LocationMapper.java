package nl.davefemi.weatherdashboard.etl.mapper.domain;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.etl.domain.service.registry.LocationDescription;
import nl.davefemi.weatherdashboard.etl.database.entity.LocationEntity;
import nl.davefemi.weatherdashboard.etl.domain.model.LocationModel;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LocationMapper {
    private final ContinentMapper continentMapper;

    public LocationModel mapToModel(LocationDescription locationDescription){
        LocationModel model = new LocationModel();
        model.setId(locationDescription.getLocation().getId());
        model.setName(locationDescription.getLocation().getName());
        model.setRegion(locationDescription.getLocation().getRegion());
        model.setCountry(locationDescription.getLocation().getCountry());
        model.setContinent(locationDescription.getLocation().getContinent());
        model.setLat(locationDescription.getLocation().getLat());
        model.setLon(locationDescription.getLocation().getLon());
        model.setTimezone(locationDescription.getLocation().getTimezone());
        return model;
    }

    public LocationModel mapToModel(LocationEntity locationEntity){
        LocationModel model = new LocationModel();
        model.setId(locationEntity.getId());
        model.setName(locationEntity.getName());
        model.setRegion(locationEntity.getRegion());
        model.setCountry(locationEntity.getCountry());
        model.setContinent(continentMapper.mapToModel(locationEntity.getContinent_name()));
        model.setLat(locationEntity.getLat());
        model.setLon(locationEntity.getLon());
        model.setTimezone(locationEntity.getTimezone());
        return model;
    }
}
