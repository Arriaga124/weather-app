package com.myproject.weatherapp.service;

import com.myproject.weatherapp.model.ForecastResponse;
import com.myproject.weatherapp.model.WeatherResponse;

public interface WeatherService {
	
	public WeatherResponse getWeather(String city, String country_code);
	
	public ForecastResponse getForecast(String city, String country_code);
}
