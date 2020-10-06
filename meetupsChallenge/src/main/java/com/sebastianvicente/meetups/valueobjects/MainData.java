package com.sebastianvicente.meetups.valueobjects;

public class MainData {
	
	private Double temp;
	
	private Double feels_like;
	
	private Double temp_min;
	
	private Double temp_max;
	
	private long pressure;
	
	private long humidity;

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Double getFeels_like() {
		return feels_like;
	}

	public void setFeels_like(Double feels_like) {
		this.feels_like = feels_like;
	}

	public Double getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(Double temp_min) {
		this.temp_min = temp_min;
	}

	public Double getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(Double temp_max) {
		this.temp_max = temp_max;
	}

	public long getPressure() {
		return pressure;
	}

	public void setPressure(long pressure) {
		this.pressure = pressure;
	}

	public long getHumidity() {
		return humidity;
	}

	public void setHumidity(long humidity) {
		this.humidity = humidity;
	}

	@Override
	public String toString() {
		return "MainData [temp=" + temp + ", feels_like=" + feels_like + ", temp_min=" + temp_min + ", temp_max="
				+ temp_max + ", pressure=" + pressure + ", humidity=" + humidity + "]";
	}
}
