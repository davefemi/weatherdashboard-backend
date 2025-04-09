package nl.davefemi.weatherdashboard.database.repository;

import nl.davefemi.weatherdashboard.database.entity.CurrentWeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentWeatherRepository extends JpaRepository<CurrentWeatherEntity, Long> {
}
