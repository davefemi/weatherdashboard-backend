package nl.davefemi.weatherdashboard.data.mapper.entity;

import nl.davefemi.weatherdashboard.data.entity.JsonRawDataEntity;
import nl.davefemi.weatherdashboard.data.model.JsonRawDataModel;
import org.springframework.stereotype.Component;

@Component
public class JsonRawDataEntityMapper {

    public JsonRawDataEntity mapToEntity(JsonRawDataModel rawJsonDataModel) {
        JsonRawDataEntity entity = new JsonRawDataEntity();
        entity.setPayload(rawJsonDataModel.getPayload());
        return entity;
    }
}
