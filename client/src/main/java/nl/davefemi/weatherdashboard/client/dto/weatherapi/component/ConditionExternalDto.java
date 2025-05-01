package nl.davefemi.weatherdashboard.client.dto.weatherapi.component;

import lombok.Data;

@Data
public class ConditionExternalDto {
    private String text;
    private String icon;
    private long code;
}
