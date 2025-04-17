package nl.davefemi.weatherdashboard.domain.service.registry;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.client.ApiClient;
import nl.davefemi.weatherdashboard.database.repository.ApiClientRepository;
import nl.davefemi.weatherdashboard.domain.model.ApiClientModel;
import nl.davefemi.weatherdashboard.mapper.domain.ApiClientMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiClientRegistry {
    private Map<ApiClientIdentifier, ApiClientDescription> registry = new HashMap<>();
    private final ApiClientRepository repository;
    private final ApiClientMapper mapper;
    private final ApplicationContext applicationContext;

    @PostConstruct
    private void init() {
        Map<ApiClientIdentifier, ApiClientModel> databaseMap = new HashMap<>();
        repository.findAll().forEach(apiClientEntity -> {
            ApiClientModel model = mapper.mapToModel(apiClientEntity);
            ApiClientIdentifier id =
                    new ApiClientIdentifier(model.getName(),
                            model.getEndpoint());
            databaseMap.put(id, model);
        });

        Map<String, ApiClient> clientBeans = applicationContext.getBeansOfType(ApiClient.class);
        for (ApiClient apiClient : clientBeans.values()) {
            ApiClientInfo info = apiClient.getClass().getAnnotation(ApiClientInfo.class);
            log.info("ApiClientInfo: {}", info);
            if (info == null) {
                throw new RuntimeException("ApiClientInfo not found");
            }
            ApiClientIdentifier id = new ApiClientIdentifier(info.name(), info.endpoint());
            ApiClientModel model = databaseMap.get(id);
            if (model == null) {
                throw new RuntimeException("ApiClientModel not found");
            }
            ApiClientDescription description = new ApiClientDescription(apiClient, databaseMap.get(model));
            registry.put(id, new ApiClientDescription(apiClient, model));
        }
    }

    public ApiClientDescription getApiClientDescription(ApiClient apiClient) {
        ApiClientInfo info = apiClient.getClass().getAnnotation(ApiClientInfo.class);
        if (info == null) {
            throw new RuntimeException("ApiClientInfo not found");
        }
        return registry.get(new ApiClientIdentifier(info.name(), info.endpoint()));
    }
}
