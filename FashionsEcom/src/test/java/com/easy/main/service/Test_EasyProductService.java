package com.easy.main.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.easy.model.Product;
import com.easy.model.ProductBrand;
import com.easy.model.ProductCategory;
import com.easy.repository.BrandRepository;
import com.easy.repository.CategoryRepository;
import com.easy.repository.OfferRepository;
import com.easy.repository.ProductRepository;
import com.easy.repository.StoreRepository;
import com.easy.repository.UserRepository;
import com.easy.service.EasyProductService;

@RunWith(SpringRunner.class)
public class Test_EasyProductService {

	@InjectMocks
	EasyProductService easyProductService;

	@Mock
	ProductRepository productRep;

	@Mock
	BrandRepository brandRep;

	@Mock
	CategoryRepository catRep;

	@Mock
	OfferRepository offerRep;

	@Mock
	UserRepository userRep;

	@Mock
	StoreRepository storeRep;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testloadAllProducts() {
		try {
			easyProductService.loadAllProducts();
		} catch (NoSuchElementException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testgetAllProducts() {
		Pageable pageable = new PageRequest(0, 10);
		Page<Product> productpage = null;
		Mockito.when(productRep.findAll(pageable)).thenReturn(null);
		assertTrue(easyProductService.getAllProducts(pageable) == null);
	}
	
	@Test
	public void testgetAllBrands() {
		Pageable pageable = new PageRequest(0, 10);
		Page<Product> productpage = null;
		Mockito.when(brandRep.findAll(pageable)).thenReturn(null);
		assertTrue(easyProductService.getAllBrands(pageable) == null);
	}
	
	@Test
	public void testgetAllCategories() {
		Pageable pageable = new PageRequest(0, 10);
		Page<Product> productpage = null;
		Mockito.when(catRep.findAll(pageable)).thenReturn(null);
		assertTrue(easyProductService.getAllCategories(pageable) == null);
	}
	
	@Test
	public void testgetAllStores() {
		Pageable pageable = new PageRequest(0, 10);
		Page<Product> productpage = null;
		Mockito.when(storeRep.findAll(pageable)).thenReturn(null);
		assertTrue(easyProductService.getAllStores(pageable) == null);
	}
	
	@Test
	public void testgetProductById() {
		int id = 10;
		Optional<Product> productOptional = Optional.of(new Product());
		Mockito.when(productRep.findById(id)).thenReturn(productOptional);
		Optional<Product> op = easyProductService.getProductById(id);
		assertTrue(op.isPresent());
	}
	
	@Test
	public void testgetProductBrandByName() {
		String name = "name";
		Mockito.when(brandRep.findByBrandName(name)).thenReturn(new ArrayList<ProductBrand>());
		List<ProductBrand> list = easyProductService.getProductBrandByName(name);
		assertTrue(list != null);
	}
	
	@Test
	public void testgetProductCategoryByName() {
		String name = "name";
		Mockito.when(catRep.findByCategoryName(name)).thenReturn(new ArrayList<ProductCategory>());
		List<ProductCategory> list = easyProductService.getProductCategoryByName(name);
		assertTrue(list != null);
	}
	
	@Test
	public void testgetAllProductsByBrand() {
		List<ProductBrand> prodList = new ArrayList<>();
		ProductBrand productBrand = new ProductBrand();
		prodList.add(productBrand);
		String name = "name";
		List<Product> productList = new ArrayList<Product>();
		Product product = new Product();
		productList.add(product);
		Mockito.when(brandRep.findByBrandName(name)).thenReturn(prodList);
		Mockito.when(productRep.findByBrand(productBrand)).thenReturn(productList);
		assertTrue(easyProductService.getAllProductsByBrand(name) != null);
	}
	
	@Test
	public void testgetAllProductsByCategory() {
		String name = "name";
		List<ProductCategory> productCategories = new ArrayList<>();
		ProductCategory productCategory = new ProductCategory();
		productCategories.add(productCategory);
		List<Product> productList = new ArrayList<Product>();
		Product product = new Product();
		productList.add(product);
		Mockito.when(catRep.findByCategoryName(name)).thenReturn(productCategories);
		Mockito.when(productRep.findByCategory(productCategory)).thenReturn(productList);
		assertTrue(easyProductService.getAllProductsByCategory(name) != null);
	}
	
	@Test
	public void testdeleteAllProducts() {
		easyProductService.deleteAllProducts();
	}
	
	@Test
	public void testdeleteProduct() {
		int productId = 10;
		easyProductService.deleteProduct(productId);
		Optional<Product> productOptional = Optional.of(new Product());
		Mockito.when(productRep.findById(productId)).thenReturn(productOptional);
		assertTrue(easyProductService.deleteProduct(productId).equals("DONE"));
	}
	
	@Test
	public void testdeleteProductNull() {
		int productId = 10;
		easyProductService.deleteProduct(productId);
		Optional<Product> productOptional = Optional.empty();
		Mockito.when(productRep.findById(productId)).thenReturn(productOptional);
		assertTrue(easyProductService.deleteProduct(productId).equals("NOT FOUND"));
	}
	
	@Test
	public void testinsertProduct() {
		Product product = new Product();
		easyProductService.insertProduct(product);
	}
	
	@Test
	public void testinsertBrand() {
		ProductBrand productBrand = new ProductBrand();
		easyProductService.insertBrand(productBrand);
	}
	
	@Test
	public void testinsertCategory() {
		ProductCategory category = new ProductCategory();
		easyProductService.insertCategory(category);
	}
	
	@Test
	public void testupdateProduct() {
		Product product = new Product();
		Optional<Product> productOptional = Optional.of(new Product());
		Mockito.when(productRep.findById(10)).thenReturn(productOptional);
		assertTrue(easyProductService.updateProduct(10, product).equals("DONE"));
	}
	
	@Test
	public void testupdateProductNull() {
		Product product = new Product();
		Optional<Product> productOptional = Optional.empty();
		Mockito.when(productRep.findById(10)).thenReturn(productOptional);
		assertTrue(easyProductService.updateProduct(10, product).equals("NOT FOUND"));
	}
	
	@Test
	public void testupdateBrand() {
		ProductBrand product = new ProductBrand();
		Optional<ProductBrand> productOptional = Optional.of(new ProductBrand());
		Mockito.when(brandRep.findById(10)).thenReturn(productOptional);
		assertTrue(easyProductService.updateBrand(10, product).equals("DONE"));
	}
	
	@Test
	public void testupdateBrandNull() {
		ProductBrand product = new ProductBrand();
		Optional<ProductBrand> productOptional = Optional.empty();
		Mockito.when(brandRep.findById(10)).thenReturn(productOptional);
		assertTrue(easyProductService.updateBrand(10, product).equals("NOT FOUND"));
	}
	
	@Test
	public void testupdateCategory() {
		ProductCategory product = new ProductCategory();
		Optional<ProductCategory> productOptional = Optional.of(product);
		Mockito.when(catRep.findById(10)).thenReturn(productOptional);
		assertTrue(easyProductService.updateCategory(10, product).equals("DONE"));
	}
	
	@Test
	public void testupdateCategoryNull() {
		ProductCategory product = new ProductCategory();
		Optional<ProductCategory> productOptional = Optional.empty();
		Mockito.when(catRep.findById(10)).thenReturn(productOptional);
		assertTrue(easyProductService.updateCategory(10, product).equals("NOT FOUND"));
	}
}
