package nl.davefemi.weatherdashboard.etl.client;

import nl.davefemi.weatherdashboard.etl.dto.external.ExternalDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public interface ApiClient {

    ExternalDto getExternalDto(String response);

    String getResponseJson(String location);
}
