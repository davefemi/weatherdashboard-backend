package nl.davefemi.weatherdashboard.etl.database.repository;

import nl.davefemi.weatherdashboard.etl.database.entity.ForecastDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForecastDayRepository extends JpaRepository<ForecastDayEntity, Long> {

}
