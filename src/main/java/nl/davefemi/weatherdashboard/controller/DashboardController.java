package nl.davefemi.weatherdashboard.controller;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nl.davefemi.weatherdashboard.service.DashboardService;

@RestController
@RequestMapping("/public/weather/{location}")
public class DashboardController {

    private final DashboardService service;

    @Autowired
    public DashboardController(DashboardService service){
        this.service = service;
    }

    @GetMapping("/fetch-current-weather")
    public String getCurrentWeather(@PathVariable("location") String location) throws MalformedURLException, URISyntaxException{
        return service.getJsonFromApi(location);
    }
}
