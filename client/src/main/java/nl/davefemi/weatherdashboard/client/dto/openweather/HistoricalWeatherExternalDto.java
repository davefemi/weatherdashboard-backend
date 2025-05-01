package nl.davefemi.weatherdashboard.client.dto.openweather;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.client.dto.ExternalDto;
import com.univocity.parsers.annotations.Parsed;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Data
public class HistoricalWeatherExternalDto implements ExternalDto {
    @Parsed(field = "city_name")
    private String cityName;

    @Parsed(field = "lat")
    private Float lat;

    @Parsed(field = "lon")
    private Float lon;

    @Parsed(field = "temp")
    private Float temp;

    @Parsed(field = "feels_like")
    private Float feelsLike;

    @Parsed(field = "temp_min")
    private Float tempMin;

    @Parsed(field = "temp_max")
    private Float tempMax;

    @Parsed(field = "pressure")
    private Long pressure;

    @Parsed(field = "sea_level")
    private Long seaLevel;

    @Parsed(field = "grnd_level")
    private Long grndLevel;

    @Parsed(field = "humidity")
    private Long humidity;

    @Parsed(field = "wind_speed")
    private Float windSpeed;

    @Parsed(field = "wind_deg")
    private Long windDeg;

    @Parsed(field = "rain_1h")
    private Float rain1h;

    @Parsed(field = "rain_3h")
    private Float rain3h;

    @Parsed(field = "snow_1h")
    private Float snow1h;

    @Parsed(field = "snow_3h")
    private Float snow3h;

    @Parsed(field = "clouds_all")
    private Long cloudsAll;

    @Parsed(field = "weather_id")
    private Long weatherId;

    @Parsed(field = "weather_main")
    private String weatherMain;

    @Parsed(field = "weather_description")
    private String weatherDescription;

    @Parsed(field = "weather_icon")
    private String weatherIcon;

    public static List<String> getExpectedHeaders() {
        List<String> headers = new ArrayList<>();
        for (Field field : HistoricalWeatherExternalDto.class.getDeclaredFields()) {
            if (field.isAnnotationPresent(Parsed.class)) {
                Parsed p = field.getAnnotation(Parsed.class);
                headers.addAll(Arrays.asList(p.field()));
            }
        }
        return headers;
    }
}
