package com.myproject.weatherapp.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.weatherapp.model.ForecastResponse;
import com.myproject.weatherapp.model.ForecastResponse.TimeStamps;
import com.myproject.weatherapp.model.WeatherResponse;

@Service
public class WeatherServiceImpl implements WeatherService {

	private RestTemplate restTemplate;
	private Geocoder geocoder;
	private ObjectMapper objectMapper;

	// OpenWeather api key
	@Value("${weather.api.key}")
	private String api_key;
	private String weather_url = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&units=imperial&appid={API key}";
	private String forecast_url = "https://api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}&units=imperial&cnt=40&appid={API key}";
	
	public WeatherServiceImpl(RestTemplate restTemplate, Geocoder geocoder, ObjectMapper objectMapper) {
		this.restTemplate = restTemplate;
		this.geocoder = geocoder;
		this.objectMapper = objectMapper;
	}

	@Override
	public WeatherResponse getWeather(String city, String country_code) {
		WeatherResponse weatherResponse = new WeatherResponse();
		Map<String, String> map = geocoder.getCoordinates(city, country_code);
		
		if(map.isEmpty())
			return null;
		
		String newUrl = weather_url.replace("{lat}", map.get("latitude")).replace("{lon}", map.get("longitude")).replace("{API key}", api_key);
 
		ResponseEntity<String> response = restTemplate.getForEntity(newUrl, String.class); 
		
		System.out.println("weather" + newUrl);
		 
		try {
			weatherResponse = objectMapper.readValue(response.getBody(), WeatherResponse.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return weatherResponse;
	}

	@Override
	public ForecastResponse getForecast(String city, String country_code) {
		ForecastResponse forecast = new ForecastResponse();
		Map<String, String> map = geocoder.getCoordinates(city, country_code);
		
		if(map.isEmpty())
			return null;
		
		String newUrl = forecast_url.replace("{lat}", map.get("latitude")).replace("{lon}", map.get("longitude")).replace("{API key}", api_key);
		
		ResponseEntity<String> response = restTemplate.getForEntity(newUrl, String.class);
		
		System.out.println("forecast: " + newUrl);
		
		try {
			forecast = objectMapper.readValue(response.getBody(), ForecastResponse.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return forecast;
	}
}
