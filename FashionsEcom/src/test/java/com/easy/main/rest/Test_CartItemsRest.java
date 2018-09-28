package com.easy.main.rest;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.easy.model.Cart;
import com.easy.model.CartItem;
import com.easy.model.User;
import com.easy.repository.UserRepository;
import com.easy.rest.CartItemsRest;
import com.easy.service.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(CartItemsRest.class)
public class Test_CartItemsRest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	CartService cartService;
	
	private Cart cart;

	@Before
	public void setUp() throws Exception {
		cart = new Cart();
		cart.setId(1);
		cart.setPriceTotal(10);
		cart.setSavings(5);
		cart.setUser(new User());
		cart.setCartItems(new HashSet<CartItem>());
	}

	@Test
	public void testAddtoCart() throws Exception {
		Mockito.when(cartService.addToCart((Cart)Mockito.any())).thenReturn(cart);
		mvc.perform(MockMvcRequestBuilders.post("/easy/product/cart")
				.contentType(MediaType.APPLICATION_JSON)
				.content(getJSON(cart)))
				.andExpect(status().isCreated())
		.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void testdeleteCart() throws Exception {
		String user = "user";
		mvc.perform(MockMvcRequestBuilders.delete("/easy/product/cart/"+user)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
		.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void testGetAllCartforUser() throws Exception {
		String user = "user";
		List<Cart> carts = new ArrayList<>();
		carts.add(cart);
		Mockito.when(cartService.getAllCartDetailsforUser(user)).thenReturn(carts);
		mvc.perform(MockMvcRequestBuilders.get("/easy/product/cart/"+user)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
		.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void testGetSavingsonCart() throws Exception {
		String user = "user";
		Mockito.when(cartService.getSavingsOnCart(user)).thenReturn(10);
		mvc.perform(MockMvcRequestBuilders.get("/easy/product/cart/savings/" + user)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
		.andExpect(jsonPath("$").isNotEmpty());
	}
	
	
	
	private String getJSON (Cart cart) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(cart);
		return jsonInString;
	}
}
