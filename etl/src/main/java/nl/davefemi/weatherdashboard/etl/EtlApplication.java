package nl.davefemi.weatherdashboard.etl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EtlApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtlApplication.class, args);
    }

}
