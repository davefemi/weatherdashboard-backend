package nl.davefemi.weatherdashboard.database.repository;

import nl.davefemi.weatherdashboard.database.entity.DailyForecastEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyForecastRepository extends JpaRepository<DailyForecastEntity, Long> {
}
