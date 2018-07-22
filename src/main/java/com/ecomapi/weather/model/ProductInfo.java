package com.ecomapi.weather.model;

public class ProductInfo {
	
	private long id = 0l;
	private String name = "";
	private long price = 0l;
	
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
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "ProductInfo [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
}
