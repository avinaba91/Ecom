package com.ecomapi.weather.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomapi.weather.model.Product;
import com.ecomapi.weather.model.ProductInfo;
import com.ecomapi.weather.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	
	public void insertProduct(Product product) {
		productRepository.insert(product);
	}
	
	public Product getProduct(long id) {
		Product product = productRepository.getProduct(id);
		return product;
	}
	
	public void deleteProductType(long id) {
		productRepository.deleteProductType(id);
	}
	
	public List<ProductInfo> getProducts() {
		List<Product> products = productRepository.getAllProducts();
		List<ProductInfo> productInfos = new ArrayList<>();
		products.forEach(product -> {
			ProductInfo productInfo = new ProductInfo();
			productInfo.setId(product.getId());
			productInfo.setName(product.getName());
			productInfo.setPrice(product.getPrice());
			productInfos.add(productInfo);
		});
		return productInfos;
	}
	
	public void removeProduct(long id) {
		productRepository.removeProduct(id);
	}
}
