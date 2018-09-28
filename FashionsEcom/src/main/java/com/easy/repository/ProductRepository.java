package com.easy.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.easy.model.Product;
import com.easy.model.ProductBrand;
import com.easy.model.ProductCategory;

public interface ProductRepository extends PagingAndSortingRepository<Product,Integer>{
//	 public List<Product> findBy(Integer manufacturerId);
//	  public List<Product> findBySegmentId(Integer segmentId);
	public List<Product> findByBrand (ProductBrand brand);
	public List<Product> findByCategory (ProductCategory category);
}
		


