package com.easy.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.easy.model.Offer;
import com.easy.model.Product;
import com.easy.model.ProductBrand;
import com.easy.model.ProductCategory;
import com.easy.model.ProductStore;
import com.easy.model.User;
import com.easy.repository.BrandRepository;
import com.easy.repository.CategoryRepository;
import com.easy.repository.OfferRepository;
import com.easy.repository.ProductRepository;
import com.easy.repository.StoreRepository;
import com.easy.repository.UserRepository;

@Service
public class EasyProductService {

	@Autowired
	ProductRepository productRep;

	@Autowired
	BrandRepository brandRep;

	@Autowired
	CategoryRepository catRep;

	@Autowired
	OfferRepository offerRep;

	@Autowired
	UserRepository userRep;

	@Autowired
	StoreRepository storeRep;

	public void loadAllProducts() {
		readCategoryjsonData();
		readBrandjsonData();
		readOfferJsonData();
		readProductjsondata();
		readUserjsonData();
	}

	public void readUserjsonData() {
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("user.json"));

			JSONArray jsonarray = (JSONArray) obj;
			User usr = new User();
			List<User> usrList = new ArrayList<User>();
			for (int i = 0; i < jsonarray.size(); i++) {
				usr = new User();
				JSONObject jsonObject = (JSONObject) jsonarray.get(i);
				usr.setId(((Long) jsonObject.get("id")).intValue());
				usr.setUserName(String.valueOf(jsonObject.get("username")));
				usrList.add(usr);

			}
			userRep.saveAll((Iterable<User>) usrList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void readCategoryjsonData() {
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("category.json"));

			JSONArray jsonarray = (JSONArray) obj;
			ProductCategory cd = new ProductCategory();
			List<ProductCategory> cdList = new ArrayList<ProductCategory>();
			for (int i = 0; i < jsonarray.size(); i++) {
				cd = new ProductCategory();
				JSONObject jsonObject = (JSONObject) jsonarray.get(i);
				Long l = (Long) jsonObject.get("ID");
				cd.setId(l.intValue());
				cd.setCategoryName(String.valueOf(jsonObject.get("CategoryName")));
				cdList.add(cd);

			}
			catRep.saveAll((Iterable<ProductCategory>) cdList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void readBrandjsonData() {
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("brand.json"));

			JSONArray jsonarray = (JSONArray) obj;
			ProductBrand bd = new ProductBrand();
			List<ProductBrand> bdList = new ArrayList<ProductBrand>();
			for (int i = 0; i < jsonarray.size(); i++) {
				bd = new ProductBrand();
				JSONObject jsonObject = (JSONObject) jsonarray.get(i);
				Long l = (Long) jsonObject.get("ID");
				bd.setId(l.intValue());
				bd.setBrandName(String.valueOf(jsonObject.get("BrandName")));
				bdList.add(bd);

			}
			brandRep.saveAll((Iterable<ProductBrand>) bdList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void readOfferJsonData() {
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("offer.json"));

			JSONArray jsonarray = (JSONArray) obj;
			Offer offer = new Offer();
			List<Offer> offList = new ArrayList<Offer>();
			for (int i = 0; i < jsonarray.size(); i++) {
				offer = new Offer();
				JSONObject jsonObject = (JSONObject) jsonarray.get(i);
				Long l = (Long) jsonObject.get("Id");
				offer.setOfferid(l.intValue());
				offer.setOfferCode(String.valueOf(jsonObject.get("OfferCode")));
				offer.setPercentOff(((Long) jsonObject.get("percentOff")).intValue());
				offer.setBundleSize(((Long) jsonObject.get("bundleSize")).intValue());
				offer.setReducedUnitPrice(((Long) jsonObject.get("reducedUnitPrice")).intValue());
				offer.setThresholdQuan(((Long) jsonObject.get("thresholdQuan")).intValue());
				offer.setPriorityOrder(((Long) jsonObject.get("priorityOrder")).intValue());
				offList.add(offer);

			}
			offerRep.saveAll((Iterable<Offer>) offList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void readProductjsondata() {
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("products.json"));

			JSONArray jsonarray = (JSONArray) obj;
			// System.out.println(jsonarray);
			Product pd = new Product();
			List<Product> pdList = new ArrayList<Product>();
			for (int i = 0; i < jsonarray.size(); i++) {
				pd = new Product();
				JSONObject jsonObject = (JSONObject) jsonarray.get(i);
				Long l = (Long) jsonObject.get("ID");
				pd.setId(l.intValue());
				pd.setPrice(((Long) jsonObject.get("Price")).intValue());
				pd.setSex((String) jsonObject.get("Sex"));
				pd.setUrl((String) jsonObject.get("Url"));
				pd.setDescription((String) jsonObject.get("Description"));
				pd.setSex((String) jsonObject.get("Sex"));
				Long bdIdlong = (Long) jsonObject.get("BrandId");
				Optional<ProductBrand> pdbd = brandRep.findById(bdIdlong.intValue());
				pd.setBrand(pdbd.get());
				Long catId = (Long) jsonObject.get("CategoryId");
				Optional<ProductCategory> pdCd = catRep.findById(catId.intValue());
				pd.setCategory(pdCd.get());
				JSONArray offArray = (JSONArray) jsonObject.get("offers");
				Set<Offer> offSet = new HashSet<Offer>();
				for (int j = 0; j < offArray.size(); j++) {
					Offer off = null;
					Long offerInt = (Long) offArray.get(j);
					if (offerInt != null && offerInt.intValue() > 0) {
						off = offerRep.findById(offerInt.intValue()).get();
						offSet.add(off);
					}
				}
				pd.setOffer(offSet);
				pdList.add(pd);

			}
			productRep.saveAll((Iterable<Product>) pdList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public Page<Product> getAllProducts(Pageable pageable) {
		Page<Product> productpage = productRep.findAll(pageable);
		return productpage;
	}

	public Page<ProductBrand> getAllBrands(Pageable pageable) {
		Page<ProductBrand> brandpage = brandRep.findAll(pageable);
		return brandpage;
	}

	public Page<ProductCategory> getAllCategories(Pageable pageable) {
		Page<ProductCategory> catpage = catRep.findAll(pageable);
		return catpage;
	}

	public Page<ProductStore> getAllStores(Pageable pageable) {
		Page<ProductStore> storepage = storeRep.findAll(pageable);
		return storepage;
	}

	public Optional<Product> getProductById(int id) {
		return productRep.findById(id);
	}

	public List<ProductBrand> getProductBrandByName(String name) {
		return brandRep.findByBrandName(name);
	}

	public List<ProductCategory> getProductCategoryByName(String name) {
		return catRep.findByCategoryName(name);
	}

	public List<Product> getAllProductsByBrand(String brandName) {
		List<ProductBrand> brlist = getProductBrandByName(brandName);

		List<Product> finalProductList = new ArrayList<Product>();
		for (ProductBrand br : brlist) {
			List<Product> productList = new ArrayList<Product>();
			productList = productRep.findByBrand(br);
			for (Product pr : productList) {
				finalProductList.add(pr);
			}
		}
		return finalProductList;
	}

	public List<Product> getAllProductsByCategory(String categoryName) {
		List<ProductCategory> ctlist = getProductCategoryByName(categoryName);

		List<Product> finalProductList = new ArrayList<Product>();
		for (ProductCategory ct : ctlist) {
			List<Product> productList = new ArrayList<Product>();
			productList = productRep.findByCategory(ct);
			for (Product pr : productList) {
				finalProductList.add(pr);
			}
		}
		return finalProductList;
	}

	public void deleteAllProducts() {
		brandRep.deleteAll();
		catRep.deleteAll();
		productRep.deleteAll();
	}

	public String deleteProduct(int productId) {
		Optional<Product> pr = productRep.findById(productId);
		if (pr.isPresent()) {
			productRep.delete(pr.get());
			return "DONE";
		} else
			return "NOT FOUND";
	}

	public void insertProduct(Product prd) {
		productRep.save(prd);
	}

	public void insertBrand(ProductBrand brand) {
		brandRep.save(brand);
	}

	public void insertCategory(ProductCategory category) {
		catRep.save(category);
	}

	public String updateProduct(int productId, Product prd) {
		Optional<Product> pr = productRep.findById(productId);
		if (pr.isPresent()) {
			productRep.save(prd);
			return "DONE";
		} else {
			return "NOT FOUND";
		}
	}

	public String updateBrand(int brandId, ProductBrand brand) {
		Optional<ProductBrand> pr = brandRep.findById(brandId);
		if (pr.isPresent()) {
			brandRep.save(brand);
			return "DONE";
		} else {
			return "NOT FOUND";
		}
	}

	public String updateCategory(int categoryId, ProductCategory category) {
		Optional<ProductCategory> pr = catRep.findById(categoryId);
		if (pr.isPresent()) {
			catRep.save(category);
			return "DONE";
		} else {
			return "NOT FOUND";
		}
	}

}
