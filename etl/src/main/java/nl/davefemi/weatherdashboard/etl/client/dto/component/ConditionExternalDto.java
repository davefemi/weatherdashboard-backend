package nl.davefemi.weatherdashboard.etl.client.dto.component;

import lombok.Data;

@Data
public class ConditionExternalDto {
    private String text;
    private String icon;
    private long code;
}
