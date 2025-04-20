package nl.davefemi.weatherdashboard.etl.mapper.domain;

import nl.davefemi.weatherdashboard.etl.service.registry.LocationDescription;
import nl.davefemi.weatherdashboard.data.entity.ContinentEntity;
import nl.davefemi.weatherdashboard.data.model.ContinentModel;
import org.springframework.stereotype.Component;

@Component
public class ContinentMapper {

    public ContinentModel mapToModel(LocationDescription locationDescription) {
        return locationDescription.getContinent();
    }



    public ContinentModel mapToModel(ContinentEntity continentEntity) {
        ContinentModel model = new ContinentModel();
        model.setName(continentEntity.getName());
        return model;
    }
}
