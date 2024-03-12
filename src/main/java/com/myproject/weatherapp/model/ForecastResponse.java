package com.myproject.weatherapp.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForecastResponse {
	private int cnt;
	@JsonProperty("list")
	private ArrayList<TimeStamps> timeStamps;
	
	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public ArrayList<TimeStamps> getTimeStamps() {
		return timeStamps;
	}

	public void setTimeStamps(ArrayList<TimeStamps> timeStamps) {
		this.timeStamps = timeStamps;
	}
	
	@Override
	public String toString() {
		return "ForecastResponse [cnt=" + cnt + ", timeStamps=" + timeStamps + "]";
	}

	

	public static class TimeStamps{
		@JsonProperty("main")
		public Data data;
		public ArrayList<Weather> weather;
		public Wind wind;
		public double pop; // Probability of precipitation
		public Sys sys;
		@JsonProperty("dt_txt")
		public String date_time;
		
		
	}
	
	public static class Data{
		public int temp;
		public int humidity;
		
	}
	
	public static class Weather {
		public int id;
		@JsonProperty("main")
		public String weather;
		public String description;
		public String icon;
	}
	
	public static class Wind {
		public double speed;
	}
	
	public static class Sys {
		public String pod; // Part of the day (n - night, d - day)
	}

}
