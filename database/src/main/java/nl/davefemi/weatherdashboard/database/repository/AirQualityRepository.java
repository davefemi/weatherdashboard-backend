package nl.davefemi.weatherdashboard.database.repository;

import nl.davefemi.weatherdashboard.database.entity.AirQualityEntity;
import nl.davefemi.weatherdashboard.database.entity.RealtimeWeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirQualityRepository extends JpaRepository <AirQualityEntity, RealtimeWeatherEntity> {
}
