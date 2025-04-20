package nl.davefemi.weatherdashboard.etl.mapper.entity;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.data.entity.LocationEntity;
import nl.davefemi.weatherdashboard.data.model.LocationModel;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LocationEntityMapper {
    private final ContinentEntityMapper continentEntityMapper;

    public LocationEntity mapToEntity(LocationModel locationModel){
        LocationEntity entity = new LocationEntity();
        entity.setId(locationModel.getId());
        entity.setName(locationModel.getName());
        entity.setRegion(locationModel.getRegion());
        entity.setCountry(locationModel.getCountry());
        entity.setContinent_name(continentEntityMapper.mapToEntity(locationModel.getContinent()));
        entity.setLat(locationModel.getLat());
        entity.setLon(locationModel.getLon());
        entity.setTimezone(locationModel.getTimezone());
        return entity;
    }
}
