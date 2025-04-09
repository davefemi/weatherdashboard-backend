package nl.davefemi.weatherdashboard.client;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.dto.external.CurrentWeatherExternalDto;
import nl.davefemi.weatherdashboard.dto.external.interval.AirQualityExternalDto;
import nl.davefemi.weatherdashboard.dto.external.interval.ConditionExternalDto;
import nl.davefemi.weatherdashboard.dto.external.interval.CurrentExternalDto;
import nl.davefemi.weatherdashboard.dto.external.location.LocationExternalDto;
import nl.davefemi.weatherdashboard.mapper.CurrentWeatherMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@Component
@RequiredArgsConstructor
public class CurrentWeatherClient implements ApiClient {
    @Value("${api.weatherapi.key}")
    private String apiKey;
    @Value("${api.weatherapi.url.current-weather}")
    private String apiUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final CurrentWeatherMapper currentWeatherMapper;
    private final DateTimeFormatter dateTimeFormatter;

    @SneakyThrows
    @Override
    public CurrentWeatherExternalDto getExternalDto(String location)  {
        String url = String.format(apiUrl, apiKey, location);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Failed to fetch weather data");
        }
        log.info("Response {}", response.getBody());
        return parseApiResponse(objectMapper.readTree(response.getBody()));
    }

    private CurrentWeatherExternalDto parseApiResponse (JsonNode apiResponse){
        CurrentWeatherExternalDto dto = new CurrentWeatherExternalDto();
        JsonNode location = apiResponse.path("location");
        JsonNode current = apiResponse.path("current");
        dto.setLocation(parseLocation(location));
        dto.setCurrent(parseCurrent(current));
        return dto;
    }

    private LocationExternalDto parseLocation(JsonNode location){
        LocationExternalDto dto = new LocationExternalDto();
        dto.setCity(location.path("name").asText());
        dto.setRegion(location.path("region").asText());
        dto.setCountry(location.path("country").asText());
        dto.setTimezone(location.path("tz_id").asText());
        dto.setLocalTimeEpoch(Instant.ofEpochSecond(location.path("localtime_epcoh").asLong()));
        dto.setLocalTime(LocalDateTime.parse(location.path("localtime").asText(), dateTimeFormatter));
        return dto;
    }

    private CurrentExternalDto parseCurrent(JsonNode current){
        CurrentExternalDto dto = new CurrentExternalDto();
        dto.setLastUpdatedEpoch(Instant.ofEpochSecond(current.path("last_updated_epoch").asLong()));
        dto.setLastUpdated(LocalDateTime.parse(current.path("last_updated").asText(), dateTimeFormatter));
        dto.setTemperature_C(Float.parseFloat(current.path("temp_c").asText()));
        dto.setTemperature_F(Float.parseFloat(current.path("temp_f").asText()));
        dto.setDay(Integer.parseInt(current.path("is_day").asText()));
        //Get and set ConditionDto
        JsonNode condition = current.path("condition");
        dto.setCondition(parseCondition(condition));
        //Resume setting other fields
        dto.setWindMph(Float.parseFloat(current.path("wind_mph").asText()));
        dto.setWindKph(Float.parseFloat(current.path("wind_kph").asText()));
        dto.setWindDegree(Long.parseLong(current.path("wind_degree").asText()));
        dto.setWindDirection((current.path("wind_dir").asText()));
        dto.setPressureMb(Float.parseFloat(current.path("pressure_mb").asText()));
        dto.setPressureIn(Float.parseFloat(current.path("pressure_in").asText()));
        dto.setPrecipitationMM(Float.parseFloat(current.path("precip_mm").asText()));
        dto.setPrecipiationIn(Float.parseFloat(current.path("precip_in").asText()));
        dto.setHumidity(Float.parseFloat(current.path("humidity").asText()));
        dto.setCloud(Long.parseLong(current.path("cloud").asText()));
        dto.setFeelsLike_C(Float.parseFloat(current.path("feelslike_c").asText()));
        dto.setFeelsLike_F(Float.parseFloat(current.path("feelslike_f").asText()));
        dto.setWindchill_C(Float.parseFloat(current.path("windchill_c").asText()));
        dto.setWindchill_F(Float.parseFloat(current.path("windchill_f").asText()));
        dto.setHeatIndex_C(Float.parseFloat(current.path("heatindex_c").asText()));
        dto.setHeatIndex_F(Float.parseFloat(current.path("heatindex_f").asText()));
        dto.setDewpoint_C(Float.parseFloat(current.path("dewpoint_c").asText()));
        dto.setDewpoint_F(Float.parseFloat(current.path("dewpoint_f").asText()));
        dto.setVisibility_Km(Float.parseFloat(current.path("vis_km").asText()));
        dto.setVisibility_Miles(Float.parseFloat(current.path("vis_miles").asText()));
        dto.setUv(Float.parseFloat(current.path("uv").asText()));
        dto.setGustMph(Float.parseFloat(current.path("gust_mph").asText()));
        dto.setGustKph(Float.parseFloat(current.path("gust_kph").asText()));
        //Get and set AirQualityDto
        JsonNode airQuality = current.path("air_quality");
        dto.setAirQualityDto(parseAirQuality(airQuality));
        return dto;
    }

    private ConditionExternalDto parseCondition(JsonNode condition){
        ConditionExternalDto dto = new ConditionExternalDto();
        dto.setText(condition.path("text").asText());
        dto.setIcon(condition.path("icon").asText());
        dto.setCode(Long.parseLong(condition.path("code").asText()));
        return dto;
    }

    private AirQualityExternalDto parseAirQuality(JsonNode airQuality){
        AirQualityExternalDto dto = new AirQualityExternalDto();
        dto.setCo(Float.parseFloat(airQuality.path("co").asText()));
        dto.setNo2(Float.parseFloat(airQuality.path("no2").asText()));
        dto.setO3(Float.parseFloat(airQuality.path("o3").asText()));
        dto.setSo2(Float.parseFloat(airQuality.path("so2").asText()));
        dto.setPm2_5(Float.parseFloat(airQuality.path("pm2_5").asText()));
        dto.setPm10(Float.parseFloat(airQuality.path("pm10").asText()));
        dto.setUs_epa_index(Long.parseLong(airQuality.path("us-epa-index").asText()));
        dto.setGb_defra_index(Long.parseLong(airQuality.path("gb-defra-index").asText()));
        return dto;
    }


}
