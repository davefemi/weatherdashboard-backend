package nl.davefemi.weatherdashboard.database.repository;

import jakarta.transaction.Transactional;
import nl.davefemi.weatherdashboard.database.entity.WeatherConditionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherConditionRepository extends JpaRepository<WeatherConditionEntity, Long> {

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO weather_condition (code, text) VALUES (:code, :text) ON CONFLICT (code) DO NOTHING" ,
            nativeQuery = true
    )
    void insertIfNotExists(@Param("code") Long code, @Param("text") String text);
}
