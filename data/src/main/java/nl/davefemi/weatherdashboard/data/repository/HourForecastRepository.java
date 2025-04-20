package nl.davefemi.weatherdashboard.data.repository;

import nl.davefemi.weatherdashboard.data.entity.HourForecastEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HourForecastRepository extends JpaRepository<HourForecastEntity, Long> {
}
