package nl.davefemi.weatherdashboard.data.repository;

import nl.davefemi.weatherdashboard.data.entity.ForecastDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForecastDayRepository extends JpaRepository<ForecastDayEntity, Long> {

}
