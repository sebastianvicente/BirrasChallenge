package com.sebastianvicente.meetups.valueobjects;

public class Wind {
	
	private Double speed;
	
	private long deg;

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public long getDeg() {
		return deg;
	}

	public void setDeg(long deg) {
		this.deg = deg;
	}

	@Override
	public String toString() {
		return "Wind [speed=" + speed + ", deg=" + deg + "]";
	}
	
	

}
