package nl.davefemi.weatherdashboard.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "forecastday", uniqueConstraints = @UniqueConstraint(columnNames = {"weather_fetch_location_id", "forecast_date"}))
@Getter
@Setter
public class ForecastDayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "weather_fetch_location_id", nullable = false)
    private WeatherFetchLocationEntity weatherFetchLocation;

    @Column(name = "forecast_date", nullable = false)
    private LocalDate forecastDate;

    private LocalTime sunrise;
    private LocalTime sunset;
    private LocalTime moonrise;
    private LocalTime moonset;

    @Column(name = "moon_illumination")
    private Long moonIllumination;

    @Column(name = "is_sun_up")
    private Boolean isSunUp;

    @Column(name = "is_moon_up")
    private Boolean isMoonUp;

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

    @OneToMany(mappedBy = "forecastDay", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HourForecastEntity> hourForecasts = new ArrayList<>();

    public void addHourForecast(HourForecastEntity hourForecast) {
        hourForecasts.add(hourForecast);
        hourForecast.setForecastDay(this);
    }
}

