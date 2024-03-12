// This class uses the GeocodingAPI from OpenWeather to take in a city name and country code and convert it into coordinates

package com.myproject.weatherapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Geocoder {

	@Value("${weather.api.key}")
	private String api_key; //OpenWeather api key
	private String url = "http://api.openweathermap.org/geo/1.0/direct?q={city name},{country code}&limit=1&appid={API key}";
	private RestTemplate restTemplate;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public Geocoder(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	//returns a map containing the coordinates of the given city
	public Map<String, String> getCoordinates(String city, String country_code){

		Map<String, String> coordinates = new HashMap<String,String>();
		
		String newUrl = url.replace("{city name}", city)
				           .replace("{country code}", country_code)
				           .replace("{API key}", api_key);
		
		ResponseEntity<String> response = restTemplate.getForEntity(newUrl, String.class);
		
		try {
            // Parse the JSON array into a JsonNode
             JsonNode jsonNode = objectMapper.readTree(response.getBody());

            // Iterate through the array elements
            for (JsonNode objectNode : jsonNode) {   	
                // Access properties dynamically
                String latitude = objectNode.get("lat").asText();
                String longitude = objectNode.get("lon").asText();

                coordinates.put("latitude", latitude);
                coordinates.put("longitude", longitude);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return coordinates;

	}
	

}
