package nl.davefemi.weatherdashboard.etl.controller;

import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.client.dto.openweather.HistoricalWeatherExternalDto;
import nl.davefemi.weatherdashboard.etl.service.CsvReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import nl.davefemi.weatherdashboard.etl.service.ForecastDataUpdateService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/private/weather")
@RequiredArgsConstructor
public class UploadController {
    private final ForecastDataUpdateService forecastDataUpdateService;
    private final CsvReaderService csvReaderService;


    @GetMapping("/fetch-forecast")
    public String getWeatherForecast(){
        forecastDataUpdateService.updateForecastWeatherData();
        return """
                <html>
                <body>
                    %s
                </body>
                </html>
                """.formatted("dto.getLocation().getName()");
    }

    @PostMapping("/upload-bulk-history")
    public ResponseEntity<?> uploadBulkHistory(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.getContentType().contains("csv")) {
            return ResponseEntity.badRequest().body("Only CSV files are supported");
        }
        try{
            List<HistoricalWeatherExternalDto> dto = csvReaderService.parseHistoricalWeatherData(file.getInputStream());
            return ResponseEntity.ok().body(dto);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
