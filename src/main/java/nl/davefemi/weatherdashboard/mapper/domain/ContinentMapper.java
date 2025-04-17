package nl.davefemi.weatherdashboard.mapper.domain;

import nl.davefemi.weatherdashboard.domain.service.registry.LocationDescription;
import nl.davefemi.weatherdashboard.database.entity.ContinentEntity;
import nl.davefemi.weatherdashboard.domain.model.ContinentModel;
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
