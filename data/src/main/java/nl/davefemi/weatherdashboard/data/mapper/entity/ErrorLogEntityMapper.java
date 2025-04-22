package nl.davefemi.weatherdashboard.data.mapper.entity;

import nl.davefemi.weatherdashboard.data.entity.ErrorLogEntity;
import nl.davefemi.weatherdashboard.data.model.ErrorLogModel;
import org.springframework.stereotype.Component;

@Component
public class ErrorLogEntityMapper {

    public ErrorLogEntity mapToEntity (ErrorLogModel errorLogModel){
        ErrorLogEntity errorLogEntity = new ErrorLogEntity();
        errorLogEntity.setErrorTime(errorLogModel.getErrorTime());
        errorLogEntity.setMessage(errorLogModel.getMessage());
        return errorLogEntity;
    }
}
