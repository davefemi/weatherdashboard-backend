package nl.davefemi.weatherdashboard.client.dto.weatherapi.component;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ForecastdayExternalDto {
    private String date;
    private long date_epoch;
    private DayExternalDto day;
    private AstroExternalDto astro;
    private List<HourExternalDto> hour = new ArrayList<>();
}
