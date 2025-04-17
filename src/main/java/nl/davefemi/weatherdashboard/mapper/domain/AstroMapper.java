package nl.davefemi.weatherdashboard.mapper.domain;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.domain.model.AstroModel;
import nl.davefemi.weatherdashboard.dto.external.component.AstroExternalDto;
import org.springframework.stereotype.Component;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class AstroMapper {
    
    public AstroModel mapToModel(AstroExternalDto astroExternalDto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);
        AstroModel model = new AstroModel();
        model.setSunrise(astroExternalDto.getSunrise() != null
                ? LocalTime.parse(astroExternalDto.getSunrise(), formatter)
                : null);
        model.setSunset(astroExternalDto.getSunset() != null
                ? LocalTime.parse(astroExternalDto.getSunset(), formatter)
                : null);
        model.setMoonrise(astroExternalDto.getMoonrise() != null
                ? LocalTime.parse(astroExternalDto.getMoonrise(), formatter)
                : null);
        model.setMoonset(astroExternalDto.getMoonset() != null
                ? LocalTime.parse(astroExternalDto.getMoonset(), formatter)
                : null);
        model.setMoonIllumination(astroExternalDto.getMoon_illumination());
        model.setSunUp(astroExternalDto.getIs_sun_up()==1);
        model.setMoonUp(astroExternalDto.getIs_moon_up()==1);
        return model;
    }


}
