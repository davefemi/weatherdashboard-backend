package nl.davefemi.weatherdashboard.etl.mapper.domain;

import nl.davefemi.weatherdashboard.etl.domain.service.registry.LocationDescription;
import nl.davefemi.weatherdashboard.etl.database.entity.ContinentEntity;
import nl.davefemi.weatherdashboard.etl.domain.model.ContinentModel;
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
