package nl.davefemi.weatherdashboard.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "forecastday", uniqueConstraints = @UniqueConstraint(columnNames = {"weather_fetch_id", "forecast_date"}))
@Getter
@Setter
public class ForecastDayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "weather_fetch_id", nullable = false)
    private WeatherFetchEntity weatherFetch;

    @Column(name = "forecast_date", nullable = false)
    private LocalDate forecastDate;

    @OneToOne(mappedBy = "forecastDay",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private AstroEntity astro;

    @OneToMany(mappedBy = "forecastDay", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HourForecastEntity> hourForecasts = new ArrayList<>();


    @OneToOne(mappedBy = "forecastDay",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private DailyForecastEntity dailyForecast;
}

