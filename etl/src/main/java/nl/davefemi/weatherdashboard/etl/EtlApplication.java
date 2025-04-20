package nl.davefemi.weatherdashboard.etl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "nl.davefemi.weatherdashboard")
@EntityScan("nl.davefemi.weatherdashboard.data.entity")
@EnableJpaRepositories("nl.davefemi.weatherdashboard.data.repository")
public class EtlApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtlApplication.class, args);
    }

}
