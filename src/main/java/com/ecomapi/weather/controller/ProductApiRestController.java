package com.ecomapi.weather.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecomapi.weather.model.Product;
import com.ecomapi.weather.model.ProductInfo;
import com.ecomapi.weather.service.ProductService;

@RestController
@RequestMapping(value="/ecom")
public class ProductApiRestController {

	@Autowired
	ProductService productService;

	@ExceptionHandler(Exception.class)
	public @ResponseBody ResponseEntity<String> dataException(HttpServletRequest request, Exception ex) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		ex.printStackTrace();
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), headers,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/erase/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> erase(@PathVariable long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		productService.deleteProductType(id);
		return new ResponseEntity<String>(HttpStatus.OK.toString(), headers, HttpStatus.OK);

	}

	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> remove(@PathVariable long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		productService.removeProduct(id);
		return new ResponseEntity<String>(HttpStatus.OK.toString(), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> add(@RequestBody Product product) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		productService.insertProduct(product);
		return new ResponseEntity<String>(HttpStatus.CREATED.toString(), headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<ProductInfo>> get() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		System.out.println("in product");
		return new ResponseEntity<List<ProductInfo>>(productService.getProducts(), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Product> getProduct(@PathVariable long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=utf-8");
		return new ResponseEntity<Product>(productService.getProduct(id), headers, HttpStatus.OK);
	}
}
