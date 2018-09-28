package com.easy.main.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.easy.model.Cart;
import com.easy.model.CartItem;
import com.easy.model.Offer;
import com.easy.model.Product;
import com.easy.model.User;
import com.easy.repository.CartItemRepository;
import com.easy.repository.CartRepository;
import com.easy.repository.OfferRepository;
import com.easy.repository.UserRepository;
import com.easy.service.CartService;

@RunWith(SpringRunner.class)
public class Test_CartService {
	
	@InjectMocks
	CartService cartService;
	
	@Mock
	CartRepository cartRep;
	
	@Mock
	CartItemRepository cartItemRep;
	
	@Mock
	OfferRepository offerRep;
	
	@Mock
	UserRepository userRep;

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testGetSavingsOnCart() {
		String username = "user";
		User user = new User();
		List<User> users = new ArrayList<>();
		users.add(user);
		Cart cart = new Cart();
		cart.setSavings(10);
		List<Cart> cartList = new ArrayList<>();
		cartList.add(cart);
		Mockito.when(userRep.findByUserName(username)).thenReturn(users);
		Mockito.when(cartRep.findByUser(user)).thenReturn(cartList);
		int no = cartService.getSavingsOnCart(username);
		assertTrue(no == 10);
	}

	@Test
	public void testGetAllCartDetailsforUser() {
		String userName = "user";
		User user = new User();
		List<User> users = new ArrayList<>();
		users.add(user);
		Cart cart = new Cart();
		cart.setSavings(10);
		List<Cart> cartList = new ArrayList<>();
		cartList.add(cart);
		Mockito.when(userRep.findByUserName(userName)).thenReturn(users);
		Mockito.when(cartRep.findByUser(user)).thenReturn(cartList);
		List<Cart> carts = cartService.getAllCartDetailsforUser(userName);
		assertTrue(carts.size() == 1);
	}

	@Test
	public void testDeleteCart() {
		String userName = "user";
		User user = new User();
		List<User> users = new ArrayList<>();
		users.add(user);
		Cart cart = new Cart();
		CartItem cartItem = new CartItem();
		Set<CartItem> cartItems = new HashSet<>();
		cartItems.add(cartItem);
		cart.setSavings(10);
		cart.setCartItems(cartItems);
		List<Cart> cartList = new ArrayList<>();
		cartList.add(cart);
		Mockito.when(userRep.findByUserName(userName)).thenReturn(users);
		Mockito.when(cartRep.findByUser(user)).thenReturn(cartList);
	}

	@Test
	public void testAddToCart() {
		Cart cart = new Cart();
		CartItem cartItem = new CartItem();
		Set<CartItem> cartItems = new HashSet<>();
		cartItems.add(cartItem);
		cart.setSavings(10);
		cart.setCartItems(cartItems);
		List<CartItem> cartItemList = new ArrayList<>();
		cartItemList.add(cartItem);
		Mockito.when(cartItemRep.findByCart(cart)).thenReturn(cartItemList);
	}

	@Test
	public void testCalculatePricingForGroupOffer() {
		Product product = new Product();
		product.setPrice(10);
		List<Offer> offerList = new ArrayList<>();
		Offer offer = new Offer();
		offer.setOfferCode("GROUP");
		offer.setPercentOff(10);
		offerList.add(offer);
		Mockito.when(offerRep.findByProductOrderByPriorityOrderAsc(product)).thenReturn(offerList);
		assertTrue(0 == cartService.calculatePricing(product, 1));
	}
	
	@Test
	public void testCalculatePricingForPROMOOffer() {
		Product product = new Product();
		product.setPrice(10);
		List<Offer> offerList = new ArrayList<>();
		Offer offer = new Offer();
		offer.setOfferCode("PROMO");
		offer.setPercentOff(10);
		offerList.add(offer);
		Mockito.when(offerRep.findByProductOrderByPriorityOrderAsc(product)).thenReturn(offerList);
		assertTrue(9 == cartService.calculatePricing(product, 1));
	}
	
	@Test
	public void testCalculatePricingForBUNDLEOffer() {
		Product product = new Product();
		product.setPrice(10);
		List<Offer> offerList = new ArrayList<>();
		Offer offer = new Offer();
		offer.setOfferCode("BUNDLE");
		offer.setPercentOff(10);
		offerList.add(offer);
		Mockito.when(offerRep.findByProductOrderByPriorityOrderAsc(product)).thenReturn(offerList);
		System.out.println(cartService.calculatePricing(product, 1));
		assertTrue(0 == cartService.calculatePricing(product, 1));
	}
	
	@Test
	public void testCalculatePricingForDEGOffer() {
		Product product = new Product();
		product.setPrice(10);
		List<Offer> offerList = new ArrayList<>();
		Offer offer = new Offer();
		offer.setOfferCode("DEG");
		offer.setPercentOff(10);
		offerList.add(offer);
		Mockito.when(offerRep.findByProductOrderByPriorityOrderAsc(product)).thenReturn(offerList);
		System.out.println(cartService.calculatePricing(product, 1));
		assertTrue(10 == cartService.calculatePricing(product, 1));
	}
	
	@Test
	public void testloadOfferData() {
		cartService.loadOfferData();
	}
}
