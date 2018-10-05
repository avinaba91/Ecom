package com.easy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.easy.model.ProductBrand;


public interface BrandRepository extends PagingAndSortingRepository<ProductBrand,Integer>{
	List<ProductBrand> findByBrandName(String brandName);
}