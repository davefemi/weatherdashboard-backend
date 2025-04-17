package nl.davefemi.weatherdashboard.etl.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiClientModel {
    private Long id;
    private String name;
    private String endpoint;
}
