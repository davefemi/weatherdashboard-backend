package nl.davefemi.weatherdashboard.etl.mapper.entity;

import nl.davefemi.weatherdashboard.database.entity.JsonRawDataEntity;
import nl.davefemi.weatherdashboard.domain.model.JsonRawDataModel;
import org.springframework.stereotype.Component;

@Component
public class JsonRawDataEntityMapper {

    public JsonRawDataEntity mapToEntity(JsonRawDataModel rawJsonDataModel) {
        JsonRawDataEntity entity = new JsonRawDataEntity();
        entity.setPayload(rawJsonDataModel.getPayload());
        return entity;
    }
}
