package nl.davefemi.weatherdashboard.etl.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "realtime_weather")
@Getter
@Setter
public class RealtimeWeatherEntity {
    @Id
    @Column(name= "weather_fetch_location_id")
    private Long id;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "weather_fetch_location_id", nullable = false)
    private WeatherFetchLocationEntity weatherFetchLocation;

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

    @OneToOne(mappedBy = "realtimeWeather",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER,
            optional = false)
    private AirQualityEntity airQuality;

    public void setAirQuality(AirQualityEntity airQuality) {
        this.airQuality = airQuality;
        airQuality.setRealtimeWeather(this);
    }
}

