package com.easy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.easy.model.ProductCategory;


public interface CategoryRepository extends PagingAndSortingRepository<ProductCategory,Integer>{
	List<ProductCategory> findByCategoryName(String categoryName);
}