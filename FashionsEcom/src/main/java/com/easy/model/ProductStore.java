package com.easy.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name = "ProductStore")
public class ProductStore implements Serializable{
	
	@Id
	int storeid;
	String location;
	String storeName;
	
	 @ManyToMany(fetch = FetchType.LAZY,
	            cascade = {
	                CascadeType.PERSIST,
	                CascadeType.MERGE
	            })
	  @JoinTable(name="ProductStoreBrand", joinColumns=@JoinColumn(name="storeid", referencedColumnName="storeid"),
	      inverseJoinColumns=@JoinColumn(name="brandid", referencedColumnName="brandid"))
	    private Set<ProductBrand> prdBrand = new HashSet<>();
	
		public ProductStore() {
			super();
		}
	 
	public ProductStore(int storeid,String location,String storeName) {
		this.storeid=storeid;
		this.location=location;
		this.storeName=storeName;
	}

	public int getStoreid() {
		return storeid;
	}

	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Set<ProductBrand> getPrdBrand() {
		return prdBrand;
	}

	public void setPrdBrand(Set<ProductBrand> prdBrand) {
		this.prdBrand = prdBrand;
	}

}
