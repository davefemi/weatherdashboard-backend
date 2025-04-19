package nl.davefemi.weatherdashboard.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.api.dto.WeatherHistoryResponseDto;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DashboardService {

    public WeatherHistoryResponseDto getHistoricalData(String location){
        return null;
    }

}
