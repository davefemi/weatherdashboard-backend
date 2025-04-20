package nl.davefemi.weatherdashboard.data.entity;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@Setter
@Table(name = "json_raw_data")
public class JsonRawDataEntity {
    @Id
    @Column(name = "weather_fetch_location_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "weather_fetch_location_id")
    private WeatherFetchLocationEntity weatherFetchLocation;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payload", columnDefinition = "jsonb", nullable = false)
    private JsonNode payload;
}
