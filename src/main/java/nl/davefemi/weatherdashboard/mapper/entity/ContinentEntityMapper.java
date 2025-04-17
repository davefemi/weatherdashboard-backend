package nl.davefemi.weatherdashboard.mapper.entity;

import nl.davefemi.weatherdashboard.database.entity.ContinentEntity;
import nl.davefemi.weatherdashboard.domain.model.ContinentModel;
import org.springframework.stereotype.Component;

@Component
public class ContinentEntityMapper {

    public ContinentEntity mapToEntity(ContinentModel continentModel) {
        ContinentEntity entity = new ContinentEntity();
        entity.setName(continentModel.getName());
        return entity;
    }
}
