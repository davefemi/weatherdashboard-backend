package nl.davefemi.weatherdashboard.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "weather_condition")
@Getter
@Setter
public class WeatherConditionEntity {
    @Id
    private Long code;  // Primary key

    private String text;
}

