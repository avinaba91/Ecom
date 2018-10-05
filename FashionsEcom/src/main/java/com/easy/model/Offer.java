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
@Table (name = "Offer")
public class Offer implements Serializable{
	@Id
	int offerid;
	String offerCode;
	String OfferDesc;
	int thresholdQuan;
	int reducedUnitPrice;
	int priorityOrder;
	int percentOff;
	int bundleSize;
	String className;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
  @JoinTable(name="ProductOffer", joinColumns=@JoinColumn(name="offerid", referencedColumnName="offerid"),
      inverseJoinColumns=@JoinColumn(name="id", referencedColumnName="id"))
    private Set<Product> product = new HashSet<Product>();
	
	public Offer() {
		super();
	}
	
	public int getOfferid() {
		return offerid;
	}
	public void setOfferid(int offerid) {
		this.offerid = offerid;
	}
	public String getOfferCode() {
		return offerCode;
	}
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	public String getOfferDesc() {
		return OfferDesc;
	}
	public void setOfferDesc(String offerDesc) {
		OfferDesc = offerDesc;
	}
//	public Set<Product> getProduct() {
//		return product;
//	}
//	public void setProduct(Set<Product> product) {
//		this.product = product;
//	}
	public int getThresholdQuan() {
		return thresholdQuan;
	}
	public void setThresholdQuan(int thresholdQuan) {
		this.thresholdQuan = thresholdQuan;
	}
	public int getPriorityOrder() {
		return priorityOrder;
	}
	public void setPriorityOrder(int priorityOrder) {
		this.priorityOrder = priorityOrder;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getReducedUnitPrice() {
		return reducedUnitPrice;
	}
	public void setReducedUnitPrice(int reducedUnitPrice) {
		this.reducedUnitPrice = reducedUnitPrice;
	}
	public int getPercentOff() {
		return percentOff;
	}
	public void setPercentOff(int percentOff) {
		this.percentOff = percentOff;
	}
	public int getBundleSize() {
		return bundleSize;
	}
	public void setBundleSize(int bundleSize) {
		this.bundleSize = bundleSize;
	}
}
