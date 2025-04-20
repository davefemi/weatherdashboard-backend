package nl.davefemi.weatherdashboard.data.mapper.domain;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.data.entity.HourForecastEntity;
import nl.davefemi.weatherdashboard.data.model.HourForecastModel;
import nl.davefemi.weatherdashboard.client.dto.component.HourExternalDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HourForecastMapper {
    private final WeatherConditionMapper weatherConditionMapper;
    private final WeatherFetchMapper weatherFetchMapper;
    private final ForecastDayMapper forecastDayMapper;

    public HourForecastModel mapToModel (HourExternalDto hourExternalDto) {
        HourForecastModel model = new HourForecastModel();
        model.setTimeEpoch(hourExternalDto.getTime_epoch());
        model.setTemperatureC(hourExternalDto.getTemp_c());
        model.setCondition((weatherConditionMapper.mapToModel(hourExternalDto.getCondition())));
        model.setDay(hourExternalDto.getIs_day()==1);
        model.setWindKph(hourExternalDto.getWind_kph());
        model.setWindDegree(hourExternalDto.getWind_degree());
        model.setWindDirection(hourExternalDto.getWind_dir());
        model.setPressureMb(hourExternalDto.getPressure_mb());
        model.setPrecipitationMm(hourExternalDto.getPrecip_mm());
        model.setHumidity(hourExternalDto.getHumidity());
        model.setCloud(hourExternalDto.getCloud());
        model.setFeelsLikeC(hourExternalDto.getFeelslike_c());
        model.setWindchillC(hourExternalDto.getWindchill_c());
        model.setHeatindexC(hourExternalDto.getHeatindex_c());
        model.setDewpointC(hourExternalDto.getDewpoint_c());
        model.setChanceOfRain(hourExternalDto.getChance_of_rain());
        model.setChanceOfSnow(hourExternalDto.getChance_of_snow());
        model.setVisibilityKm(hourExternalDto.getVis_km());
        model.setGustKph(hourExternalDto.getGust_kph());
        model.setUv(hourExternalDto.getUv());
        return model;
    }



    public HourForecastModel mapToModel (HourForecastEntity hourForecast) {
        HourForecastModel model = new HourForecastModel();
        model.setId(hourForecast.getId());
        model.setForecastday(forecastDayMapper.mapToModel(hourForecast.getForecastDay()));
        model.setTimeEpoch(hourForecast.getTimeEpoch());
        model.setTemperatureC(hourForecast.getTemperatureC());
        model.setCondition(weatherConditionMapper.mapToModel(hourForecast.getCondition()));
        model.setDay(hourForecast.getIsDay());
        model.setWindKph(hourForecast.getWindKph());
        model.setWindDegree(hourForecast.getWindDegree());
        model.setWindDirection(hourForecast.getWindDirection());
        model.setPressureMb(hourForecast.getPressureMb());
        model.setPrecipitationMm(hourForecast.getPrecipitationMm());
        model.setSnowCm(hourForecast.getSnowCm());
        model.setHumidity(hourForecast.getHumidity());
        model.setCloud(hourForecast.getCloud());
        model.setFeelsLikeC(hourForecast.getFeelslikeC());
        model.setWindchillC(hourForecast.getWindchillC());
        model.setHeatindexC(hourForecast.getHeatindexC());
        model.setDewpointC(hourForecast.getDewpointC());
        model.setChanceOfRain(hourForecast.getChanceOfRain());
        model.setChanceOfSnow(hourForecast.getChanceOfSnow());
        model.setVisibilityKm(hourForecast.getVisibilityKm());
        model.setGustKph(hourForecast.getGustKph());
        model.setUv(hourForecast.getUv());
        return model;
    }
}
