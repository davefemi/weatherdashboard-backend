package nl.davefemi.weatherdashboard.client.api;

import nl.davefemi.weatherdashboard.client.dto.ExternalDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public interface ApiClient {

    ExternalDto getExternalDto(String response);

//    ApiResponse getApiResponse(String location);
}
