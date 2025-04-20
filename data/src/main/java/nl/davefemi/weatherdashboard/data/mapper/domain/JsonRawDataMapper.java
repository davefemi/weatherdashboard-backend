package nl.davefemi.weatherdashboard.data.mapper.domain;

import com.fasterxml.jackson.databind.JsonNode;
import nl.davefemi.weatherdashboard.data.model.JsonRawDataModel;
import org.springframework.stereotype.Component;

@Component
public class JsonRawDataMapper {

    public JsonRawDataModel mapToModel(JsonNode rawData) {
        JsonRawDataModel model = new JsonRawDataModel();
        model.setPayload(rawData);
        return model;
    }
}
