package nl.davefemi.weatherdashboard.mapper.entity;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.database.entity.AstroEntity;
import nl.davefemi.weatherdashboard.domain.model.AstroModel;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AstroEntityMapper {

    public AstroEntity mapToEntity (AstroModel astroModel){
        AstroEntity entity = new AstroEntity();
        entity.setSunrise(astroModel.getSunrise());
        entity.setSunset(astroModel.getSunset());
        entity.setMoonrise(astroModel.getMoonrise());
        entity.setMoonset(astroModel.getMoonset());
        entity.setMoonIllumination(astroModel.getMoonIllumination());
        entity.setIsSunUp(astroModel.isSunUp());
        entity.setIsMoonUp(astroModel.isMoonUp());
        return entity;
    }
}
