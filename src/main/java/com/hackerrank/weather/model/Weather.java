package com.hackerrank.weather.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather implements Serializable {
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date date;
	
	@JsonProperty(value = "location")
	private Location location;
	private Float[] temperature;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Float[] getTemperature() {
		return temperature;
	}

	public void setTemperature(Float[] temperature) {
		this.temperature = temperature;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Weather [id=");
		builder.append(id);
		builder.append(", date=");
		builder.append(date);
		builder.append(", location=");
		builder.append(location);
		builder.append(", temperature=");
		builder.append(Arrays.toString(temperature));
		builder.append("]");
		return builder.toString();
	}

}
