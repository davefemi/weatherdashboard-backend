package nl.davefemi.weatherdashboard.etl.service;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.common.processor.BeanListProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.davefemi.weatherdashboard.client.dto.openweather.HistoricalWeatherExternalDto;
import org.springframework.stereotype.Service;
import reactor.core.Exceptions;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CsvReaderService {

    public List<HistoricalWeatherExternalDto> parseHistoricalWeatherData(InputStream input) {
        List<String> expectedHeaders = HistoricalWeatherExternalDto.getExpectedHeaders();
        BeanListProcessor<HistoricalWeatherExternalDto> processor = new BeanListProcessor<>(HistoricalWeatherExternalDto.class);
        CsvParserSettings settings = new CsvParserSettings();
        settings.setProcessor(processor);
        settings.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(settings);
        parser.parse(new InputStreamReader(input));
        for (String header : expectedHeaders) {
            if (!Arrays.asList(processor.getHeaders()).contains(header)){
                log.info("Headers {}", Arrays.asList(processor.getHeaders()).getFirst());
                throw Exceptions.propagate(new IllegalArgumentException("Does not contain the right headers"));
            }
        }
        List<HistoricalWeatherExternalDto> dto = processor.getBeans();
        log.info("Headers {}", Arrays.asList(processor.getHeaders()));
        log.info(dto.toString());
        return dto;
    }
}
