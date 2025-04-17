package nl.davefemi.weatherdashboard.database.entity;

import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "weather_fetch_location")
public class WeatherFetchLocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "weather_fetch_id", nullable = false)
    private WeatherFetchEntity weatherFetch;

    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity location;

    private LocalDateTime localTime;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "raw_json_data", columnDefinition = "jsonb", nullable = false)
    private JsonNode rawJsonData;


    @OneToMany(mappedBy = "weatherFetchLocation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ForecastDayEntity> forecastdays = new ArrayList<>();

    @OneToOne(mappedBy = "weatherFetchLocation", cascade = CascadeType.ALL, orphanRemoval = true)
    private RealtimeWeatherEntity realtimeWeather;

    public void setRealtimeWeather(RealtimeWeatherEntity realtimeWeather) {
        this.realtimeWeather = realtimeWeather;
        realtimeWeather.setWeatherFetchLocation(this);
    }

    public void addForecastDay(ForecastDayEntity forecastDay) {
        forecastdays.add(forecastDay);
        forecastDay.setWeatherFetchLocation(this);
    }
}
