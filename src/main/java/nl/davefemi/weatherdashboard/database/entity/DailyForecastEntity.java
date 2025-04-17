package nl.davefemi.weatherdashboard.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "daily_forecast")
@Getter
@Setter
public class DailyForecastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Reference to the associated ForecastDay
    @OneToOne(optional = false)
    @JoinColumn(name = "forecastday_id", nullable = false, unique = true)
    private ForecastDayEntity forecastDay;

    @Column(name = "maxtemperature_c")
    private Float maxtemperatureC;

    @Column(name = "mintemperature_c")
    private Float mintemperatureC;

    @Column(name = "avgtemperature_c")
    private Float avgtemperatureC;

    @Column(name = "maxwind_kph")
    private Float maxwindKph;

    @Column(name = "totalprecipitaton_mm")
    private Float totalprecipitatonMm;

    @Column(name = "totalsnow_cm")
    private Float totalsnowCm;

    @Column(name = "avgvisibility_km")
    private Float avgvisibilityKm;

    @Column(name = "avghumidity")
    private Long avghumidity;

    @Column(name = "chance_of_rain")
    private Long chanceOfRain;

    @Column(name = "chance_of_snow")
    private Long chanceOfSnow;

    @ManyToOne(optional = false)
    @JoinColumn(name = "condition_code", nullable = false)
    private WeatherConditionEntity condition;

    private Float uv;
}

