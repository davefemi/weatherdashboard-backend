package nl.davefemi.weatherdashboard.client.api.client;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
public class LocalTimeFallbackDeserializer extends JsonDeserializer<String> {
    private DateTimeFormatter localTimeFormatter;

    @PostConstruct
    public void init() {
        this.localTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            LocalTime.parse(p.getText(), localTimeFormatter);
        }
        catch (Exception e) {
            log.info("Unexpected value encountered {} ", e.getMessage());
            return null;
        }
        return p.getText();
    }
}
