package nl.davefemi.weatherdashboard.etl.domain.service.registry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nl.davefemi.weatherdashboard.etl.client.ApiClient;
import nl.davefemi.weatherdashboard.etl.domain.model.ApiClientModel;

@RequiredArgsConstructor
@Getter
@Setter
public class ApiClientDescription {
    private final ApiClient apiClient;
    private final ApiClientModel apiClientModel;
}
