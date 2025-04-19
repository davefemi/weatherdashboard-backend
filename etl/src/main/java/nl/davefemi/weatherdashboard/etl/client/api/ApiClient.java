package nl.davefemi.weatherdashboard.etl.client.api;

import nl.davefemi.weatherdashboard.etl.client.dto.ExternalDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public interface ApiClient {

    ExternalDto getExternalDto(String response);

    String getResponseJson(String location);
}
