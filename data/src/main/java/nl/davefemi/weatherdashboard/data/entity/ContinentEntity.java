package nl.davefemi.weatherdashboard.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "continent")
@Getter
@Setter
public class ContinentEntity {
    @Id
    private String name;

}
