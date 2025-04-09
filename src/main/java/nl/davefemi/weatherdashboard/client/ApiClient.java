package nl.davefemi.weatherdashboard.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.davefemi.weatherdashboard.dto.external.ExternalDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
@Service
public interface ApiClient {

    ExternalDto getExternalDto(String location);
}
