package nl.davefemi.weatherdashboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateTimeFormatterConfig {

    @Bean
    public DateTimeFormatter getDateTimeFormatter(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
}
