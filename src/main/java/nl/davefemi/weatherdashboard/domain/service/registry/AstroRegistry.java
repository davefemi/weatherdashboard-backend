package nl.davefemi.weatherdashboard.domain.service.registry;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.database.repository.AstroRepository;
import nl.davefemi.weatherdashboard.domain.model.AstroModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AstroRegistry {
    private final AstroRepository astroRepository;
    private final Map<LocalDate, AstroModel> astros = new HashMap<>();

    @PostConstruct
    public void init() {
        astroRepository.findAll().forEach(astro -> {

        });
    }

}
