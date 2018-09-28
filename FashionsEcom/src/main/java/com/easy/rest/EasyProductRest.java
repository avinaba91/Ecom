package com.easy.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.easy.model.Cart;
import com.easy.model.Product;
import com.easy.model.ProductBrand;
import com.easy.model.ProductCategory;
import com.easy.model.ProductStore;
import com.easy.service.EasyProductService;


@RestController
public class EasyProductRest {
	
	@Autowired
	EasyProductService productService;
	
	@RequestMapping(value="/easy/product", method = RequestMethod.POST)
	public ResponseEntity<String> loadAllProducts(){
		productService.loadAllProducts();
		return new ResponseEntity<String>("Data Loaded", new HttpHeaders(),HttpStatus.OK);
	}

	@RequestMapping(value="/easy/product", method = RequestMethod.GET)
	public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable){
		Page<Product> productList=null;
		productList=productService.getAllProducts(pageable);
		if(productList==null || (productList!=null && productList.getContent().size()==0)) {
			return new ResponseEntity<Page<Product>>(productList, new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Page<Product>>(productList, new HttpHeaders(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/easy/product/brand", method = RequestMethod.GET)
	public ResponseEntity<Page<ProductBrand>> getAllBrands(Pageable pageable){
		Page<ProductBrand> brandList=null;
		brandList=productService.getAllBrands(pageable);
		if(brandList==null || (brandList!=null && brandList.getContent().size()==0)) {
			return new ResponseEntity<Page<ProductBrand>>(brandList, new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Page<ProductBrand>>(brandList, new HttpHeaders(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/easy/product/category", method = RequestMethod.GET)
	public ResponseEntity<Page<ProductCategory>> getAllCatgories(Pageable pageable){
		Page<ProductCategory> catList=null;
		catList=productService.getAllCategories(pageable);
		if(catList==null || (catList!=null && catList.getContent().size()==0)) {
			return new ResponseEntity<Page<ProductCategory>>(catList, new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Page<ProductCategory>>(catList, new HttpHeaders(),HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/easy/product/{prdid}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProductById(@PathVariable  int prdid){
		Optional<Product> pd=productService.getProductById(prdid);
		if(!pd.isPresent()) {
			return new ResponseEntity<Product>(new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(pd.get(), new HttpHeaders(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/easy/product/brand/{brandName}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getAllProductsbyBrand(@PathVariable String brandName){
		List<Product> productList=null;
		productList=productService.getAllProductsByBrand(brandName);
		if(productList==null || (productList!=null && productList.size()==0)) {
			return new ResponseEntity<List<Product>>(productList, new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Product>>(productList, new HttpHeaders(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/easy/product/category/{categoryName}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getAllProductsbyCategory(@PathVariable String categoryName){
		List<Product> productList=null;
		productList=productService.getAllProductsByCategory(categoryName);
		if(productList==null || (productList!=null && productList.size()==0)) {
			return new ResponseEntity<List<Product>>(productList, new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Product>>(productList, new HttpHeaders(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/easy/product/{productId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateProduct(@PathVariable int productId, @RequestBody Product prd){
		String val=productService.updateProduct(productId, prd);
		if(val.equals("DONE")) {
			return new ResponseEntity<String>("Updated", new HttpHeaders(),HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Nor found", new HttpHeaders(),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/easy/product/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> insertProduct(@RequestBody Product prd){
		productService.insertProduct(prd);
		return new ResponseEntity<String>("Updated", new HttpHeaders(),HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/easy/product/", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteAllProducts(){
		productService.deleteAllProducts();
		return new ResponseEntity<String>("Deleted", new HttpHeaders(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/easy/product/{productId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteProducts(@PathVariable int productId){
		String val=productService.deleteProduct(productId);
		if(val.equals("DONE")) {
			return new ResponseEntity<String>("Deleted", new HttpHeaders(),HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Deleted", new HttpHeaders(),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/easy/product/brand/{brandId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateBrand(@PathVariable int brandId, @RequestBody ProductBrand brd){
		String val=productService.updateBrand(brandId, brd);
		if(val.equals("DONE")) {
			return new ResponseEntity<String>("Updated", new HttpHeaders(),HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Nor found", new HttpHeaders(),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/easy/product/category/{catId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateCategory(@PathVariable int catId, @RequestBody ProductCategory cat){
		String val=productService.updateCategory(catId, cat);
		if(val.equals("DONE")) {
			return new ResponseEntity<String>("Updated", new HttpHeaders(),HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Nor found", new HttpHeaders(),HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/easy/product/brand", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> insertBrand(@RequestBody ProductBrand brand){
		productService.insertBrand(brand);
		return new ResponseEntity<String>("Updated", new HttpHeaders(),HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/easy/product/category", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> insertBrand(@RequestBody ProductCategory category){
		productService.insertCategory(category);
		return new ResponseEntity<String>("Updated", new HttpHeaders(),HttpStatus.CREATED);
	}
	
}
