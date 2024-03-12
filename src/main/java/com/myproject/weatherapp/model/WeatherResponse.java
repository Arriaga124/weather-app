package com.myproject.weatherapp.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {
	private Coord coord;
	private ArrayList<Weather> weather;
	@JsonProperty("main")
	private Data data;
	private Wind wind;
	private String name;
	
	
	public Coord getCoord() {
		return coord;
	}
	public void setCoord(Coord coord) {
		this.coord = coord;
	}
	public ArrayList<Weather> getWeather() {
		return weather;
	}
	public void setWeather(ArrayList<Weather> weather) {
		this.weather = weather;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "WeatherResponse [coord=" + coord + ", weather=" + weather + ", data=" + data + ", wind=" + wind + "]";
	}
	
	

public static class Coord{
	public float lon;
	public float lat;
}

public static class Weather{
	public int id;
	@JsonProperty("main")
	public String weather;
	public String description;
	public String icon;
	
}

public static class Data{
	public int temp;
	public int feels_like;
	public int temp_min;
	public int temp_max;
	public float pressure;
	public int humidity;
	
}

public static class Wind{
	public float speed;
	public float deg;
	public float gust;
	}
}

