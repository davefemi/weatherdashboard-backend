package nl.davefemi.weatherdashboard.database.repository;

import nl.davefemi.weatherdashboard.database.entity.CurrentWeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurrentWeatherRepository extends JpaRepository<CurrentWeatherEntity, Long> {

    @Query("SELECT c " +
            "FROM CurrentWeatherEntity c " +
            "WHERE c.city = :location " +
            "AND c.fetchTimestamp >= :olderThanOneHour " +
            "ORDER BY c.fetchTimestamp DESC")
    List<CurrentWeatherEntity> checkForLatestData(@Param("olderThanOneHour")
                                                      Instant olderThanOneHour, @Param("location") String location);

}
