package com.easy.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.easy.model.Cart;
import com.easy.service.CartService;

@RestController
public class CartItemsRest {

	@Autowired
	CartService cartService;
	
	
	@RequestMapping(value="/easy/product/cart", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addtoCart(@RequestBody Cart car){
		Cart crt=cartService.addToCart(car);
		return new ResponseEntity<String>(""+crt.getId(), new HttpHeaders(),HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/easy/product/cart/{username}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteCart(@PathVariable String username){
		cartService.deleteCart(username);
		return new ResponseEntity<String>("Deleted", new HttpHeaders(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/easy/product/cart/{userName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cart>> getAllCartforUser(@PathVariable String userName){
		List<Cart> crtList=cartService.getAllCartDetailsforUser(userName);
		return new ResponseEntity<List<Cart>>(crtList, new HttpHeaders(),HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/easy/product/cart/savings/{userName}", method = RequestMethod.GET)
	public ResponseEntity<String> getSavingsonCart(@PathVariable String userName){
		int savings=cartService.getSavingsOnCart(userName);
		return new ResponseEntity<String>("Savings-"+savings, new HttpHeaders(),HttpStatus.OK);
		
	}
}
