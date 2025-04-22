package nl.davefemi.weatherdashboard.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "error_log")
public class ErrorLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weather_fetch_location_id", nullable = false)
    private WeatherFetchLocationEntity weatherFetchLocation;

    @Column(name = "error_time")
    private LocalDateTime errorTime;

    @Column(name = "message")
    private String message;

}
