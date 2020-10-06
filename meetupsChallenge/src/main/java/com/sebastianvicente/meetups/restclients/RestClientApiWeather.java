package com.sebastianvicente.meetups.restclients;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import com.sebastianvicente.meetups.valueobjects.CurrentWeatherDataInfo;

@Component
public class RestClientApiWeather {

	private static final String URL_OPEN_WEATHER_MAP = "https://community-open-weather-map.p.rapidapi.com/weather";

	public CurrentWeatherDataInfo consultarClima() {

		CurrentWeatherDataInfo currentWeatherDataInfo = new CurrentWeatherDataInfo();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", "accurate");
		params.put("units", "metric");
		params.put("q", "Ciudad Autónoma de Buenos Aires");
		params.put("lang", "es");

		try {
			HttpRequest httpResponse = Unirest.get(URL_OPEN_WEATHER_MAP)
					.header("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
					.header("x-rapidapi-key", "a8c9cc0c4dmsh67bb7892e0d068dp13ccbcjsn0c806b82895f").queryString(params);

			HttpResponse<JsonNode> jsonResponse = httpResponse.asJson();
			Gson gson = new Gson();
			String responseJSONString = jsonResponse.getBody().toString();
			currentWeatherDataInfo = gson.fromJson(responseJSONString, CurrentWeatherDataInfo.class);

		} catch (UnirestException e) {
			e.printStackTrace();
			return null;
		}
		return currentWeatherDataInfo;
	}

}
