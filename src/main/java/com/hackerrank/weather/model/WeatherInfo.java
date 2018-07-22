package com.hackerrank.weather.model;

import java.io.Serializable;

public class WeatherInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 291685970966975296L;
	private float lat;
	private float lon;
	private String city = "";
	private String state = "";
	private String message = null;
	private float lowest;
	private float highest;
	
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public float getLowest() {
		return lowest;
	}
	public void setLowest(float lowest) {
		this.lowest = lowest;
	}
	public float getHighest() {
		return highest;
	}
	public void setHighest(float highest) {
		this.highest = highest;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WeatherInfo [lat=");
		builder.append(lat);
		builder.append(", lon=");
		builder.append(lon);
		builder.append(", city=");
		builder.append(city);
		builder.append(", state=");
		builder.append(state);
		builder.append(", message=");
		builder.append(message);
		builder.append(", lowest=");
		builder.append(lowest);
		builder.append(", highest=");
		builder.append(highest);
		builder.append("]");
		return builder.toString();
	}
}
