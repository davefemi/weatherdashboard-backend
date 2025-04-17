package nl.davefemi.weatherdashboard.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "astro")
@Getter
@Setter
public class AstroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "forecastday_id", nullable = false, unique = true)
    private ForecastDayEntity forecastDay;

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
}

