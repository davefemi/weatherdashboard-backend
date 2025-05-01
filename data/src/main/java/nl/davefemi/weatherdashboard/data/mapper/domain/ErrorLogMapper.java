package nl.davefemi.weatherdashboard.data.mapper.domain;

import nl.davefemi.weatherdashboard.client.dto.weatherapi.ErrorExternalDto;
import nl.davefemi.weatherdashboard.data.model.ErrorLogModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ErrorLogMapper {

    public ErrorLogModel mapToModel(ErrorExternalDto errorExternalDto) {
        ErrorLogModel model = new ErrorLogModel();
        model.setErrorTime(LocalDateTime.now());
        model.setMessage(errorExternalDto.getMessage());
        return model;
    }
}
