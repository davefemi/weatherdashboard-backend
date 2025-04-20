package nl.davefemi.weatherdashboard.data.mapper.domain;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.data.entity.ForecastDayEntity;
import nl.davefemi.weatherdashboard.data.model.ForecastDayModel;
import nl.davefemi.weatherdashboard.client.dto.component.ForecastdayExternalDto;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class ForecastDayMapper {
    private final WeatherConditionMapper weatherConditionMapper;

    public ForecastDayModel mapToModel(ForecastdayExternalDto forecastday) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.US);
        ForecastDayModel model = new ForecastDayModel();
        model.setForecastDate(LocalDate.parse(forecastday.getDate()));
        model.setSunrise(forecastday.getAstro().getSunrise() != null ? LocalTime.parse(forecastday.getAstro().getSunrise().toString(), formatter) : null);
        model.setSunset(forecastday.getAstro().getSunset() != null ? LocalTime.parse(forecastday.getAstro().getSunset(),formatter) : null);
        model.setMoonrise(forecastday.getAstro().getMoonrise() != null ? LocalTime.parse(forecastday.getAstro().getMoonrise().toString(), formatter) : null);
        model.setMoonset(forecastday.getAstro().getMoonset() != null ? LocalTime.parse(forecastday.getAstro().getMoonset(), formatter) : null);
        model.setMoonIllumination(forecastday.getAstro().getMoon_illumination());
        model.setSunUp(forecastday.getAstro().getIs_sun_up() == 1);
        model.setMoonUp(forecastday.getAstro().getIs_moon_up() == 1);
        model.setMaxtemparatureC(forecastday.getDay().getMaxtemp_c());
        model.setMintemparatureC(forecastday.getDay().getMintemp_c());
        model.setAvgtemparatureC(forecastday.getDay().getAvgtemp_c());
        model.setMaxwindKph(forecastday.getDay().getMaxwind_kph());
        model.setTotalprecipitationMm(forecastday.getDay().getTotalprecip_mm());
        model.setTotalsnowCm(forecastday.getDay().getTotalsnow_cm());
        model.setAvgvisibilityKm(forecastday.getDay().getAvgvis_km());
        model.setAvghumdity(forecastday.getDay().getAvghumidity());
        model.setChanceOfRain(forecastday.getDay().getDaily_chance_of_rain());
        model.setChanceOfSnow(forecastday.getDay().getDaily_chance_of_snow());
        model.setCondition((weatherConditionMapper.mapToModel(forecastday.getDay().getCondition())));
        model.setUv(forecastday.getDay().getUv());
        return model;
    }


    public ForecastDayModel mapToModel(ForecastDayEntity entity) {
        ForecastDayModel model = new ForecastDayModel();
        model.setId(entity.getId());
        model.setForecastDate(entity.getForecastDate());
        return model;
    }

}
