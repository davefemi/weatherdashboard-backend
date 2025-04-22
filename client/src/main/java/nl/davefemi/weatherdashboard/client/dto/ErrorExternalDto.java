package nl.davefemi.weatherdashboard.client.dto;

import lombok.Data;

@Data
public class ErrorExternalDto implements ExternalDto {
    private long code;
    private String message;
}
