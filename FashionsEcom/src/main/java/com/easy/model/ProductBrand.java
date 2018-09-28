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
import javax.persistence.Table;

@Entity
@Table(name = "ProductBrand")
public class ProductBrand implements Serializable{
	@Id
	int brandid;
	String brandName;

	public ProductBrand() {
		super();
	}

	 @ManyToMany(fetch = FetchType.LAZY,
	            cascade = {
	                CascadeType.PERSIST,
	                CascadeType.MERGE
	            })
	  @JoinTable(name="ProductStoreBrand", joinColumns=@JoinColumn(name="brandid", referencedColumnName="brandid"),
	      inverseJoinColumns=@JoinColumn(name="storeid", referencedColumnName="storeid"))
	    private Set<ProductStore> prdStore = new HashSet<>();
	 
	public ProductBrand(int brandid, String brandName) {
		super();
		this.brandid = brandid;
		this.brandName = brandName;
	}

	public int getId() {
		return brandid;
	}

	public void setId(int brandid) {
		this.brandid = brandid;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Set<ProductStore> getPrdStore() {
		return prdStore;
	}

	public void setPrdStore(Set<ProductStore> prdStore) {
		this.prdStore = prdStore;
	}

}
