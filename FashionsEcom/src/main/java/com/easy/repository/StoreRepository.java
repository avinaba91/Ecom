package com.easy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.easy.model.ProductStore;

public interface StoreRepository extends PagingAndSortingRepository<ProductStore, Integer>{

}

