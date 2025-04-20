package nl.davefemi.weatherdashboard.data.repository;

import nl.davefemi.weatherdashboard.data.entity.ContinentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinentRepository extends JpaRepository<ContinentEntity, String> {
}
