package nl.davefemi.weatherdashboard.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiClientModel {
    private Long id;
    private String name;
    private String endpoint;
}
