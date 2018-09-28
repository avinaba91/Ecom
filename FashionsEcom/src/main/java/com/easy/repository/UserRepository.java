package com.easy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.easy.model.Cart;
import com.easy.model.User;


public interface UserRepository extends CrudRepository<User,Integer>{
	List<User> findByUserName(String userName);
}