package nl.davefemi.weatherdashboard.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "CurrentWeather")
public class CurrentWeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fetch_timestamp")
    private Instant fetchTimestamp;

    @Column(name = "city")
    private String city;

    @Column(name = "region")
    private String region;

    @Column(name = "country")
    private String country;

    @Column(name = "timezone")
    private String tz_id;

    @Column(name = "localTime")
    private String localTime;

    @Column(name ="temperatureC")
    private float temperatureC;

    @Column(name = "is_day")
    private long is_day;

    @Column(name = "feelslike")
    private float feelslike_c;

    @Column(name = "condition")
    private String condition;

    @Column(name = "precip_mm")
    private float precip_mm;

    @Column(name = "cloud")
    private float cloud;
}
