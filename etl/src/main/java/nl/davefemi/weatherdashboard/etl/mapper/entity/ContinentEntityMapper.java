package nl.davefemi.weatherdashboard.etl.mapper.entity;

import nl.davefemi.weatherdashboard.data.entity.ContinentEntity;
import nl.davefemi.weatherdashboard.data.model.ContinentModel;
import org.springframework.stereotype.Component;

@Component
public class ContinentEntityMapper {

    public ContinentEntity mapToEntity(ContinentModel continentModel) {
        ContinentEntity entity = new ContinentEntity();
        entity.setName(continentModel.getName());
        return entity;
    }
}
