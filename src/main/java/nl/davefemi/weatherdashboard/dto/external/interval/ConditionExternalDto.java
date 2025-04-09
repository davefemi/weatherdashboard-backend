package nl.davefemi.weatherdashboard.dto.external.interval;

import lombok.Data;

@Data
public class ConditionExternalDto {
    private String text;
    private String icon;
    private long code;
}
