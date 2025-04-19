package nl.davefemi.weatherdashboard.etl.database.repository;

import nl.davefemi.weatherdashboard.etl.database.entity.ContinentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinentRepository extends JpaRepository<ContinentEntity, String> {
}
