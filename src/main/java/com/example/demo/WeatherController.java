package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//REST CONTROLLER FOR API
@RestController
public class WeatherController {

    private final weatherApp weatherService;

    public WeatherController(weatherApp weatherService) {
        this.weatherService = weatherService;
    }

    // Example endpoint for getting weather data
    @GetMapping("/weather")
    public String getWeather(@RequestParam double latitude, @RequestParam double longitude,@RequestParam String timezone) {
        return weatherService.CallAPI(latitude, longitude,timezone);
    }
}