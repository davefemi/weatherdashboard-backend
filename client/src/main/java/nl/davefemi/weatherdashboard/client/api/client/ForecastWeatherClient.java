package nl.davefemi.weatherdashboard.client.api.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.client.api.call.ApiCallHandler;
import nl.davefemi.weatherdashboard.client.api.call.ApiResponse;
import nl.davefemi.weatherdashboard.client.dto.ErrorExternalDto;
import nl.davefemi.weatherdashboard.client.dto.ExternalDtoAggregator;
import nl.davefemi.weatherdashboard.client.dto.ForecastWeatherExternalDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@ApiClientInfo(name = "weatherapi", endpoint = "forecast")
@Slf4j
public class ForecastWeatherClient implements ApiClient {
    @Value("${api.weatherapi.key}")
    private String apiKey;
    @Value("${api.weatherapi.url.forecast}")
    private String apiUrl;
    private final ApiCallHandler apiCallHandler;
    private final ObjectMapper objectMapper;

    public ExternalDtoAggregator getExternalDtoAggregator(List<String> locations) {
        List<ApiResponse> responses = getApiResponse(locations);
        ExternalDtoAggregator aggregator = new ExternalDtoAggregator();
        for (ApiResponse response : responses) {
            if (response.isSuccess()){
                aggregator.addExternalDto(response.getLocation(), getExternalDto(response.getResponse()));
                aggregator.addJsonRawData(response.getLocation(), getJsonRawData(response.getResponse()));
            }
            if (!response.isSuccess()){
                aggregator.addExternalDto(response.getLocation(), getErrorExternalDto(response.getResponse()));
            }
        }
        return aggregator;
    }

    private List<ApiResponse> getApiResponse(List<String> locations) {
        return apiCallHandler.getResponses(locations, apiUrl, apiKey);
    }

    @SneakyThrows
    private ForecastWeatherExternalDto getExternalDto(String response) {
        ForecastWeatherExternalDto dto = objectMapper.readValue(response, ForecastWeatherExternalDto.class);
        return dto;
    }

    private ErrorExternalDto getErrorExternalDto(String response) {
        ErrorExternalDto errorExternalDto;
        try {
            String jSonNode = response.substring(response.indexOf('{'));
            log.info(jSonNode);
            JsonNode jsonNode = objectMapper.readTree(jSonNode);
            errorExternalDto = objectMapper.treeToValue(jsonNode.path("error"), ErrorExternalDto.class);
            log.info(errorExternalDto.toString());
            return errorExternalDto;
        }
        catch (Exception e) {
            log.error(e.getMessage());
            errorExternalDto = new ErrorExternalDto();
            errorExternalDto.setMessage(response);
            log.error(errorExternalDto.toString());
            return errorExternalDto;
        }
    }

    private JsonNode getJsonRawData(String response) {
        JsonNode rawJsonData;
        try {
            rawJsonData = objectMapper.readTree(response);
        } catch (Exception e) {
            throw new RuntimeException("Could not read Json {}", e);
        }
        return rawJsonData;
    }
}

