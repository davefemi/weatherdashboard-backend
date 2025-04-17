package nl.davefemi.weatherdashboard.database.repository;

import jakarta.transaction.Transactional;
import nl.davefemi.weatherdashboard.database.entity.ForecastDayEntity;
import nl.davefemi.weatherdashboard.database.entity.WeatherFetchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

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
