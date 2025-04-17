package nl.davefemi.weatherdashboard.etl.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hour_forecast", uniqueConstraints = @UniqueConstraint(columnNames = {"forecastday_id", "time_epoch"}))
@Getter
@Setter
public class HourForecastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "forecastday_id", nullable = false)
    private ForecastDayEntity forecastDay;

    @Column(name = "time_epoch", nullable = false)
    private Long timeEpoch;

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

    @Column(name = "snow_cm")
    private Float snowCm;

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

    @Column(name = "chance_of_rain")
    private Long chanceOfRain;

    @Column(name = "chance_of_snow")
    private Long chanceOfSnow;

    @Column(name = "visibility_km")
    private Float visibilityKm;

    @Column(name = "gust_kph")
    private Float gustKph;

    private Float uv;
}

