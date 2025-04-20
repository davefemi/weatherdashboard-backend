package nl.davefemi.weatherdashboard.data.repository;

import nl.davefemi.weatherdashboard.data.entity.AirQualityEntity;
import nl.davefemi.weatherdashboard.data.entity.RealtimeWeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirQualityRepository extends JpaRepository <AirQualityEntity, RealtimeWeatherEntity> {
}
