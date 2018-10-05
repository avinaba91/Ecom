package com.easy.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ProductCategory")
public class ProductCategory implements Serializable{
	
	@Id
	int categoryid;
	String categoryName;

	public ProductCategory() {
		super();
	}

	public ProductCategory(int categoryid, String categoryName) {
		super();
		this.categoryid = categoryid;
		this.categoryName = categoryName;
	}

	public int getId() {
		return categoryid;
	}

	public void setId(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
