package nl.davefemi.weatherdashboard.domain.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonRawDataModel {
    private Long weatherFetchLocationId;
    private WeatherFetchLocationModel weatherFetchLocation;
    private JsonNode payload;
}
