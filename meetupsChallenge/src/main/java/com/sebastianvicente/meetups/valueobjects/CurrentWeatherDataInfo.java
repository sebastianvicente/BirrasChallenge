package com.sebastianvicente.meetups.valueobjects;

import java.util.List;

public class CurrentWeatherDataInfo {
	
	private Coord coord;
	
	private List<DataWeather> weather;
	
	private String base;
	
	private MainData main;
	
	private long visibility;
	
	private Wind wind;
	
	private Clouds clouds;
	
	private long dt;
	
	private Sys sys;
	
	private long timezone;
	
	private long id;
	
	private String name;
	
	private long cod;

	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public List<DataWeather> getWeather() {
		return weather;
	}

	public void setWeather(List<DataWeather> weather) {
		this.weather = weather;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public MainData getMain() {
		return main;
	}

	public void setMain(MainData main) {
		this.main = main;
	}

	public long getVisibility() {
		return visibility;
	}

	public void setVisibility(long visibility) {
		this.visibility = visibility;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Clouds getClouds() {
		return clouds;
	}

	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}

	public long getDt() {
		return dt;
	}

	public void setDt(long dt) {
		this.dt = dt;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public long getTimezone() {
		return timezone;
	}

	public void setTimezone(long timezone) {
		this.timezone = timezone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCod() {
		return cod;
	}

	public void setCod(long cod) {
		this.cod = cod;
	}

	@Override
	public String toString() {
		return "CurrentWeatherDataInfo [" + (coord != null ? "coord=" + coord + ", " : "")
				+ (weather != null ? "weather=" + weather + ", " : "") + (base != null ? "base=" + base + ", " : "")
				+ (main != null ? "main=" + main + ", " : "") + "visibility=" + visibility + ", "
				+ (wind != null ? "wind=" + wind + ", " : "") + (clouds != null ? "clouds=" + clouds + ", " : "")
				+ "dt=" + dt + ", " + (sys != null ? "sys=" + sys + ", " : "") + "timezone=" + timezone + ", id=" + id
				+ ", " + (name != null ? "name=" + name + ", " : "") + "cod=" + cod + "]";
	}
	
}
