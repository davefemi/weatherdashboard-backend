package nl.davefemi.weatherdashboard.client.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ExternalDtoAggregator {
    private Map<String, ExternalDto> externalDto = new HashMap<>();
    private Map<String, JsonNode> jsonRawData = new HashMap<>();

    public void addExternalDto(String location, ExternalDto externalDto) {
        this.externalDto.put(location, externalDto);
    }

    public void addJsonRawData(String location, JsonNode jsonRawData) {
        this.jsonRawData.put(location, jsonRawData);
    }

}
