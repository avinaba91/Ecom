package com.easy.main.rest;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.easy.model.Cart;
import com.easy.model.Product;
import com.easy.rest.CartItemsRest;
import com.easy.rest.EasyProductRest;
import com.easy.service.CartService;
import com.easy.service.EasyProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(EasyProductRest.class)
public class Test_EasyProductRest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	EasyProductService easyProductService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLoadAllProducts() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/easy/product")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
		.andExpect(jsonPath("$").isNotEmpty());
	}

	//@Test
	public void testGetAllProducts() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/easy/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(getJSON(getPageProduct())))
				.andExpect(status().isOk())
		.andExpect(jsonPath("$").isNotEmpty());
	}

	//@Test
	public void testGetAllBrands() {
		fail("Not yet implemented");
	}

	//@Test
	public void testGetAllCatgories() {
		fail("Not yet implemented");
	}

	//@Test
	public void testGetProductById() {
		fail("Not yet implemented");
	}

	//@Test
	public void testGetAllProductsbyBrand() {
		fail("Not yet implemented");
	}

	//@Test
	public void testGetAllProductsbyCategory() {
		fail("Not yet implemented");
	}

	//@Test
	public void testUpdateProduct() {
		fail("Not yet implemented");
	}

	//@Test
	public void testInsertProduct() {
		fail("Not yet implemented");
	}

	//@Test
	public void testDeleteAllProducts() {
		fail("Not yet implemented");
	}

	//@Test
	public void testDeleteProducts() {
		fail("Not yet implemented");
	}

	//@Test
	public void testUpdateBrand() {
		fail("Not yet implemented");
	}

	//@Test
	public void testUpdateCategory() {
		fail("Not yet implemented");
	}

	//@Test
	public void testInsertBrandProductBrand() {
		fail("Not yet implemented");
	}

	//@Test
	public void testInsertBrandProductCategory() {
		fail("Not yet implemented");
	}
	
	
	private String getJSON (Page<Product> productList) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(productList);
		return jsonInString;
	}
	
	private Page<Product> getPageProduct() {
		List<Product> products = new ArrayList<>();
		Product product = new Product();
		product.setId(10);
		products.add(product);
		Page<Product> productList = new PageImpl<>(products);
		return productList;
	}
}
