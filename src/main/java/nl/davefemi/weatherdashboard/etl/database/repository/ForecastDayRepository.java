package nl.davefemi.weatherdashboard.etl.database.repository;

import nl.davefemi.weatherdashboard.etl.database.entity.ForecastDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForecastDayRepository extends JpaRepository<ForecastDayEntity, Long> {

//    @Modifying
//    @Transactional
//    @Query(
//            value = "INSERT INTO forecast_day VALUES (:weatherFetch, :weatherFetch) ON CONFLICT (:date) DO NOTHING" ,
//            nativeQuery = true
//    )
//    void insertIfNotExists(@Param("weatherFetch") WeatherFetchEntity forecastDay,
//                           @Param("forecastDate") LocalDate forecastDate);
//

}
