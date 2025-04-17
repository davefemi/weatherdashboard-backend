package nl.davefemi.weatherdashboard.etl.mapper.domain;

import nl.davefemi.weatherdashboard.etl.database.entity.ApiClientEntity;
import nl.davefemi.weatherdashboard.etl.domain.model.ApiClientModel;
import org.springframework.stereotype.Component;

@Component
public class ApiClientMapper {

    public ApiClientModel mapToModel(ApiClientEntity apiClientEntity){
        ApiClientModel apiClientModel = new ApiClientModel();
        apiClientModel.setId(apiClientEntity.getId());
        apiClientModel.setName(apiClientEntity.getName());
        apiClientModel.setEndpoint(apiClientEntity.getEndpoint());
        return apiClientModel;
    }


}
