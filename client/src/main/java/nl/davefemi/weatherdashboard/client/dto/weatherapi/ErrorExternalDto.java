package nl.davefemi.weatherdashboard.client.dto.weatherapi;

import lombok.Data;
import nl.davefemi.weatherdashboard.client.dto.ExternalDto;

@Data
public class ErrorExternalDto implements ExternalDto {
    private long code;
    private String message;
}
