package nl.davefemi.weatherdashboard.etl.mapper.entity;

import nl.davefemi.weatherdashboard.database.entity.ApiClientEntity;
import nl.davefemi.weatherdashboard.domain.model.ApiClientModel;
import org.springframework.stereotype.Component;

@Component
public class ApiClientEntityMapper {

    public ApiClientEntity mapToEntity(ApiClientModel apiClientModel){
        ApiClientEntity apiClientEntity = new ApiClientEntity();
        apiClientEntity.setId(apiClientModel.getId());
        apiClientEntity.setName(apiClientModel.getName());
        apiClientEntity.setEndpoint(apiClientModel.getEndpoint());
        return apiClientEntity;
    }
}
