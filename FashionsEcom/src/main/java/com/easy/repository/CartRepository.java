package com.easy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.easy.model.Cart;
import com.easy.model.ProductBrand;
import com.easy.model.User;


public interface CartRepository extends CrudRepository<Cart,Integer>{
	List<Cart> findByUser(User user);
}
