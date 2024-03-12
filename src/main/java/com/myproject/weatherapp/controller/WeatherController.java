package com.myproject.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.weatherapp.model.ForecastResponse;
import com.myproject.weatherapp.model.WeatherResponse;
import com.myproject.weatherapp.service.WeatherService;


@Controller
public class WeatherController {
	
	@Autowired
	WeatherService service;
	
	@GetMapping("/")
	public String home() {
		
		return "home";
	}
	
	@GetMapping("/weather")
	public String weather(@RequestParam String city, @RequestParam String country, ModelMap model) {
		WeatherResponse weatherResponse = service.getWeather(city.trim(), country.trim());
		
		if(weatherResponse == null)
			return "home";
		
		String iconCode = weatherResponse.getWeather().get(0).icon;
		String iconUrl = "https://openweathermap.org/img/wn/{icon_code}@2x.png".replace("{icon_code}", iconCode);
		
		city = city.substring(0, 1).toUpperCase() + city.substring(1);
			
		model.addAttribute("weatherResponse", weatherResponse);
		model.addAttribute("city", city);
		model.addAttribute("country", country.toUpperCase());
		model.addAttribute("iconUrl",iconUrl);
		
		return "weather";
	}
	
	@GetMapping("/forecast")
	public String forecast(@RequestParam String city, @RequestParam String country, ModelMap model) {
		ForecastResponse forecastResponse = service.getForecast(city.trim(), country.trim());
		
		if(forecastResponse == null)
			return "home";
		
		model.addAttribute("forecastResponse", forecastResponse);
		model.addAttribute("city", city);
		model.addAttribute("country", country.toUpperCase());
		
		return "forecast";
	}
	

}
