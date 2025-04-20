package nl.davefemi.weatherdashboard.data.repository;

import nl.davefemi.weatherdashboard.data.entity.WeatherFetchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherFetchRepository extends JpaRepository<WeatherFetchEntity, Long> {
}
