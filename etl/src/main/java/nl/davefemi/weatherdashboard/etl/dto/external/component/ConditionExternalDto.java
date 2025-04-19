package nl.davefemi.weatherdashboard.etl.dto.external.component;

import lombok.Data;

@Data
public class ConditionExternalDto {
    private String text;
    private String icon;
    private long code;
}
