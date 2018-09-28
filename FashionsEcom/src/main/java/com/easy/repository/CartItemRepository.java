package com.easy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.easy.model.Cart;
import com.easy.model.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem,Integer>{
	List<CartItem> findByCart(Cart cart);
}
