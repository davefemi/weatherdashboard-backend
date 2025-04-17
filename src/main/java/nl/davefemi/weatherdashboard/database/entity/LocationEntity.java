package nl.davefemi.weatherdashboard.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "location")
@Getter
@Setter
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String region;
    private String country;

    @ManyToOne(optional = false)
    @JoinColumn(name = "continent_name", nullable = false)
    private ContinentEntity continent_name;

    private Float lat;
    private Float lon;

    private String timezone;

}

