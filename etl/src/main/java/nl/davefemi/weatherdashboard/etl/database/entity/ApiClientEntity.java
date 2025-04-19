package nl.davefemi.weatherdashboard.etl.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "api_client", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "endpoint"}))
@Getter
@Setter
public class ApiClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "endpoint", nullable = false)
    private String endpoint;
}

