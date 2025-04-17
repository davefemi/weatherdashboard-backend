package nl.davefemi.weatherdashboard.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "realtime_weather")
@Getter
@Setter
public class RealtimeWeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "weather_fetch_id", nullable = false)
    private WeatherFetchEntity weatherFetch;

    // Storing epoch as a Long (could also be a BigInteger or converted to a timestamp)
    @Column(name = "last_updated_epoch")
    private Long lastUpdatedEpoch;

    @Column(name = "temperature_c")
    private Float temperatureC;

    @ManyToOne(optional = false)
    @JoinColumn(name = "condition_code", nullable = false)
    private WeatherConditionEntity condition;

    @Column(name = "is_day")
    private Boolean isDay;

    @Column(name = "wind_kph")
    private Float windKph;

    @Column(name = "wind_degree")
    private Long windDegree;

    @Column(name = "wind_direction")
    private String windDirection;

    @Column(name = "pressure_mb")
    private Float pressureMb;

    @Column(name = "precipitation_mm")
    private Float precipitationMm;

    @Column(name = "humidity")
    private Float humidity;

    private Long cloud;

    @Column(name = "feelslike_c")
    private Float feelslikeC;

    @Column(name = "windchill_c")
    private Float windchillC;

    @Column(name = "heatindex_c")
    private Float heatindexC;

    @Column(name = "dewpoint_c")
    private Float dewpointC;

    @Column(name = "visibility_km")
    private Float visibilityKm;

    private Float uv;

    @Column(name = "gust_kph")
    private Float gustKph;
}

