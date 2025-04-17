package nl.davefemi.weatherdashboard.etl.database.repository;

import nl.davefemi.weatherdashboard.etl.database.entity.CurrentWeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface CurrentWeatherRepository extends JpaRepository<CurrentWeatherEntity, Long> {

    @Query("SELECT c " +
            "FROM CurrentWeatherEntity c " +
            "WHERE LOWER(c.city) = LOWER(:location) " +
            "AND c.fetchTimestamp > :fifteenMinutesAgo " +
            "ORDER BY c.fetchTimestamp DESC")
    List<CurrentWeatherEntity> checkForLatestData(@Param("fifteenMinutesAgo")
                                                      Instant fifteenMinutesAgo, @Param("location") String location);

}
