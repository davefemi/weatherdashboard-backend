package nl.davefemi.weatherdashboard.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "air_quality")
@Getter
@Setter
public class AirQualityEntity {
    @Id
    @Column(name = "realtime_weather_id")
    private Long realtimeWeatherId;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "realtime_weather_id", nullable = false)
    private RealtimeWeatherEntity realtimeWeather;

    private Float co;
    private Float no2;
    private Float o3;
    private Float so2;

    @Column(name = "pm2_5")
    private Float pm2_5;

    private Float pm10;

    // Air quality indices; column names match your DDL (us and gb)
    private Long us_epa_index;
    private Long gb_defra_index;
}

