package nl.davefemi.weatherdashboard.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "weather_fetch")
@Getter
@Setter
public class WeatherFetchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity location;

    @Column(name = "fetch_timestamp", nullable = false)
    private Instant fetchTimestamp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "api_client_id", nullable = false)
    private ApiClientEntity apiClient;

    private LocalDateTime localTime;

    @OneToMany(mappedBy = "weatherFetch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ForecastDayEntity> forecastdays = new ArrayList<>();
}

