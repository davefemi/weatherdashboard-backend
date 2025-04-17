package nl.davefemi.weatherdashboard.domain.model;

import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WeatherFetchModel {
    private Long id;
    private LocationModel location;
    private Instant fetchTimestamp;
    private ApiClientModel apiClient;
    private LocalDateTime localTime;
    private List<ForecastDayModel> forecastdays = new ArrayList<>();
}
