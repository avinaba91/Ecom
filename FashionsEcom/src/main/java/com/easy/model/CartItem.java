package com.easy.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "CartItem")
public class CartItem implements Serializable{
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@JsonIgnore
	@ManyToOne
    private Cart cart;

    @OneToOne
    private Product product;

    @NotNull
    private int quantity;

    @JsonIgnore
    private int amount;
    
    @JsonIgnore
    private int amountRevised;

//    private String status;
    
	public CartItem() {
		super();
	}
	
    public CartItem(Product product, int quantity ) {
    	this.product=product;
    	this.quantity=quantity;
    }

    private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public int getAmountRevised() {
		return amountRevised;
	}

	public void setAmountRevised(int amountRevised) {
		this.amountRevised = amountRevised;
	}
}
