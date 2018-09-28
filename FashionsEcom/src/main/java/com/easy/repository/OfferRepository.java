package com.easy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.easy.model.Offer;
import com.easy.model.Product;


public interface OfferRepository extends CrudRepository<Offer,Integer>{
	List<Offer> findByProductOrderByPriorityOrderAsc(Product pr);
}
