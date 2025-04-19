package nl.davefemi.weatherdashboard.etl.database.repository;

import nl.davefemi.weatherdashboard.etl.database.entity.WeatherFetchLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherFetchLocationRepository extends JpaRepository<WeatherFetchLocationEntity, Long> {
}
