package com.easy.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Product")
public class Product implements Item, Serializable {

	@Id
	int id;
	int price;
	String name;
	String sex;
	String url;
	String description;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "ProductOffer", joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "offerid", referencedColumnName = "offerid"))
	private Set<Offer> offer = new HashSet<Offer>();

	@JsonIgnore
	@ManyToOne
	ProductBrand brand;

	@JsonIgnore
	@ManyToOne
	ProductCategory category;

	public Product() {
		super();
	}

	public Product(int id, int price, String name, String sex, String url, String description) {// , String brandName,
		// String categoryName) {
		super();
		this.id = id;
		this.price = price;
		this.name = name;
		this.sex = sex;
		this.url = url;
		this.description = description;
		// this.brandName = brandName;
		// this.categoryName = categoryName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductBrand getBrand() {
		return brand;
	}

	public void setBrand(ProductBrand brand) {
		this.brand = brand;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	@Override
	public int price() {
		// TODO Auto-generated method stub
		return getPrice();
	}

	@Override
	public int priceForQuantity(int quantity) {
		return (price * quantity);
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Offer> getOffer() {
		return offer;
	}

	public void setOffer(Set<Offer> offer) {
		this.offer = offer;
	}

}
