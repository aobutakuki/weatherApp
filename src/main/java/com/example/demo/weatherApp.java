package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

@Configuration
class AppConfig {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}



@SpringBootApplication
public class weatherApp {
	private final RestTemplate restTemplate;

	// Constructor injection of RestTemplate
	public weatherApp(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public static void main(String[] args) {
		// Run the Spring application and get the application context
		var context = SpringApplication.run(weatherApp.class, args);

		// Get the weatherApp bean from the context
		weatherApp app = context.getBean(weatherApp.class);

		// Call the method on the bean
		String APIRETURN = app.CallAPI(-23.5475, -46.6361, "America/Sao_Paulo");
		System.out.println(APIRETURN);
	}

	public String CallAPI(double latitude, double longitude, String timezone) {
		final String OPEN_METEO_URL = "https://api.open-meteo.com/v1/forecast";
		final String OPEN_FETCH_INFORMATION = "&current=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation,wind_speed_10m&hourly=temperature_2m&daily=weather_code,temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,sunrise,sunset";

		String url_getWeather = OPEN_METEO_URL + "?latitude=" + latitude + "&longitude=" + longitude + OPEN_FETCH_INFORMATION + "&timezone=" + timezone;

		// Use the injected RestTemplate
		return restTemplate.getForObject(url_getWeather, String.class);
	}
}



