package nl.davefemi.weatherdashboard.etl.mapper.domain;

import com.fasterxml.jackson.databind.JsonNode;
import nl.davefemi.weatherdashboard.domain.model.JsonRawDataModel;
import org.springframework.stereotype.Component;

@Component
public class JsonRawDataMapper {

    public JsonRawDataModel mapToModel(JsonNode rawData) {
        JsonRawDataModel model = new JsonRawDataModel();
        model.setPayload(rawData);
        return model;
    }
}
