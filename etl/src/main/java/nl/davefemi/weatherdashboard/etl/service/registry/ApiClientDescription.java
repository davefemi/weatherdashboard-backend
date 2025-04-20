package nl.davefemi.weatherdashboard.etl.service.registry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nl.davefemi.weatherdashboard.client.api.ApiClient;
import nl.davefemi.weatherdashboard.data.model.ApiClientModel;

@RequiredArgsConstructor
@Getter
@Setter
public class ApiClientDescription {
    private final ApiClient apiClient;
    private final ApiClientModel apiClientModel;
}
