package nl.davefemi.weatherdashboard.etl.database.repository;

import nl.davefemi.weatherdashboard.etl.database.entity.WeatherFetchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherFetchRepository extends JpaRepository<WeatherFetchEntity, Long> {
}
