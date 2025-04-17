package nl.davefemi.weatherdashboard.database.repository;

import jakarta.transaction.Transactional;
import nl.davefemi.weatherdashboard.database.entity.AstroEntity;
import nl.davefemi.weatherdashboard.database.entity.ForecastDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
public interface AstroRepository extends JpaRepository<AstroEntity, ForecastDayEntity> {

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO astro (sunrise, sunset, moonrise, moonset, moon_illumination, is_sun_up, is_moon_up) " +
                    "VALUES (:forecastDayId, :sunrise, :sunset, :moonrise, :moonset, :moonIllumination, :isSunUp, :isMoonUp) " +
                    "ON CONFLICT (forecastday_id) DO NOTHING",
            nativeQuery = true
    )
    void insertIfNotExists(
                           @Param("sunrise") LocalTime sunrise, @Param("sunset") LocalTime sunset,
                           @Param("moonrise") LocalTime moonrise,  @Param("moonset") LocalTime moonset,
                           @Param("moonIllumination") Long moonIllumination,  @Param("isSunUp") Boolean isSunUp,
                           @Param("isMoonUp") Boolean isMoonUp);

}
