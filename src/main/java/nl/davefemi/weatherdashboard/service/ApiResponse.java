package nl.davefemi.weatherdashboard.service;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiResponse {

    @JsonProperty("response_code")
    private int responseCode;

    private List<Question> results;

    
}
