package nl.davefemi.weatherdashboard.etl.database.repository;

import nl.davefemi.weatherdashboard.etl.database.entity.ApiClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiClientRepository extends JpaRepository<ApiClientEntity, Long> {
}
