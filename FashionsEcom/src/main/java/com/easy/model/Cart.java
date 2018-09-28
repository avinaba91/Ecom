package com.easy.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Cart")
public class Cart implements Serializable{
	@JsonIgnore
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	
	private User user;
	
	@OneToMany (mappedBy="cart")
	private Set<CartItem> cartItems = new HashSet<CartItem>();
	
	private int priceTotal;
	
	private int savings;
	
	public Cart() {
		super();
	}
	
	public Cart(User user, int priceTotal, int savings, Set<CartItem> cartItems) {
		this.user=user;
		this.priceTotal=priceTotal;
		this.savings=savings;
		this.cartItems=cartItems;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public int getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(int priceTotal) {
		this.priceTotal = priceTotal;
	}

	public int getSavings() {
		return savings;
	}

	public void setSavings(int savings) {
		this.savings = savings;
	}
}
