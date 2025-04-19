package nl.davefemi.weatherdashboard.etl.mapper.entity;

import nl.davefemi.weatherdashboard.etl.database.entity.JsonRawDataEntity;
import nl.davefemi.weatherdashboard.etl.domain.model.JsonRawDataModel;
import org.springframework.stereotype.Component;

@Component
public class JsonRawDataEntityMapper {

    public JsonRawDataEntity mapToEntity(JsonRawDataModel rawJsonDataModel) {
        JsonRawDataEntity entity = new JsonRawDataEntity();
        entity.setPayload(rawJsonDataModel.getPayload());
        return entity;
    }
}
