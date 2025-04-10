package nl.davefemi.weatherdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeatherdashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherdashboardApplication.class, args);
	}

}
