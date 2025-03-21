package nl.davefemi.weatherdashboard.service;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@Getter
@Setter
public class ApiResponse {
    @Autowired
    JsonPOJOBuilder pojo;

    private List<Weather> results;

    
}
