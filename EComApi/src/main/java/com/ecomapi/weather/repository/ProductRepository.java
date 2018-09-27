package com.ecomapi.weather.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ecomapi.weather.model.Product;

@Component
public class ProductRepository {
	
	private Map<Long, Product> productMap = new HashMap<>();
	private long idCounter = 0l;
	
	public boolean insert(Product product) {
		boolean success = false;
		product.setId(++idCounter);
		productMap.put(product.getId(), product);
		success = true;
		return success;
	}

	public Product getProduct(long id) {
		Product product = productMap.get(id);
		return product;
	}
	
	public List<Product> getAllProducts () {
		if (CollectionUtils.isEmpty(productMap)) {
			init();
		}
		return new ArrayList<>(productMap.values());
	}
	
	public boolean deleteProductType (long id) {
		boolean success = false;
		productMap.remove(id);
		success = true;
		return success;
	}
	
	public void removeProduct(long id) {
		Product product = productMap.get(id);
		product.setQuantity(product.getQuantity()-1);
		productMap.put(id, product);
	}
	
	public void init() {
		Product product = new Product();
		product.setName("Moto e5");
		product.setPrice(10000);
		product.setQuantity(10l);
		product.setDescription("4 GB RAM, Octacore Processer");
		insert(product);
	}
}
