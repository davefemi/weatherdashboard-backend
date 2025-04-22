package nl.davefemi.weatherdashboard.data.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorLogModel {
    private Long id;
    private WeatherFetchLocationModel weatherFetchLocation;
    private LocalDateTime errorTime;
    private String message;
}
