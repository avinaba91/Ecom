package com.easy.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.model.Cart;
import com.easy.model.CartItem;
import com.easy.model.Offer;
import com.easy.model.Product;
import com.easy.model.ProductCategory;
import com.easy.model.User;
import com.easy.pricing.BundlePricing;
import com.easy.pricing.DegressivePricing;
import com.easy.pricing.GroupPricing;
import com.easy.pricing.PricingPolicy;
import com.easy.pricing.PromotionPricing;
import com.easy.repository.CartItemRepository;
import com.easy.repository.CartRepository;
import com.easy.repository.OfferRepository;
import com.easy.repository.UserRepository;

@Service
public class CartService {
	
	@Autowired
	CartRepository cartRep;
	
	@Autowired
	CartItemRepository cartItemRep;
	
	@Autowired
	OfferRepository offerRep;
	
	@Autowired
	UserRepository userRep;

	
	public void loadOfferData() {
		readOfferJsonData();
//		readProductOfferJsonData();
	}
	
	public void readOfferJsonData() {
		JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("offer.json"));

            JSONArray jsonarray = (JSONArray) obj;
            Offer offer= new Offer();
            List<Offer> offList= new ArrayList<Offer>();
            for (int i = 0; i < jsonarray.size(); i++) {
	            JSONObject jsonObject= (JSONObject) jsonarray.get(i);
	            Long l=(Long) jsonObject.get("Id");
	            offer.setOfferid(l.intValue());
	            offer.setOfferCode(String.valueOf(jsonObject.get("OfferCode")));
	            offer.setOfferDesc(String.valueOf(jsonObject.get("OfferDesc")));
	            offer.setPercentOff(((Long)jsonObject.get("percentOff")).intValue());
	            offer.setBundleSize(((Long)(jsonObject.get("bundleSize"))).intValue());
	            offer.setReducedUnitPrice(((Long)(jsonObject.get("reducedUnitPrice"))).intValue());
	            offer.setThresholdQuan(((Long)(jsonObject.get("thresholdQuan"))).intValue());
	            offer.setPriorityOrder(((Long)(jsonObject.get("priorityOrder"))).intValue());
	            offList.add(offer);
	            
            }
            offerRep.saveAll((Iterable<Offer>)offList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	public void readProductOfferJsonData() {
		JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("productOffer.json"));
            JSONArray jsonarray = (JSONArray) obj;
            Offer offer= new Offer();
            List<Offer> offList= new ArrayList<Offer>();
            for (int i = 0; i < jsonarray.size(); i++) {
	            JSONObject jsonObject= (JSONObject) jsonarray.get(i);
	            Long l=(Long) jsonObject.get("Id");
	            offer.setOfferid(l.intValue());
	            offer.setOfferCode(String.valueOf(jsonObject.get("OfferCode")));
	            offer.setOfferDesc(String.valueOf(jsonObject.get("OfferDesc")));
	            offer.setPercentOff((int)jsonObject.get("percentOff"));
	            offer.setBundleSize((int)(jsonObject.get("bundleSize")));
	            offer.setReducedUnitPrice((int)(jsonObject.get("reducedUnitPrice")));
	            offer.setThresholdQuan((int)(jsonObject.get("thresholdQuan")));
	            offer.setPriorityOrder((int)(jsonObject.get("priorityOrder")));
	            offList.add(offer);
	            
            }
            offerRep.saveAll((Iterable<Offer>)offList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	public int getSavingsOnCart(String username) {
		List<User> usrList=userRep.findByUserName(username);
		List<Cart> cartList= new ArrayList<>();
		cartList=cartRep.findByUser(usrList.get(0));
		return cartList.get(0).getSavings();
	}
	
	public List<Cart> getAllCartDetailsforUser(String userName) {
		List<User> usrList=userRep.findByUserName(userName);
		List<Cart> cartList= new ArrayList<>();
		cartList=cartRep.findByUser(usrList.get(0));
		return cartList;
	}
	
	public void deleteCart(String user) {
		List<User> usrList=userRep.findByUserName(user);
		List<Cart> cartList= new ArrayList<>();
		cartList=cartRep.findByUser(usrList.get(0));
		Set<CartItem> ctSet=cartList.get(0).getCartItems();
		for (CartItem cartItem : ctSet) {
			cartItemRep.delete(cartItem);
		}
		cartRep.delete(cartList.get(0));
	}

	public Cart addToCart(Cart cr) {
		cartRep.save(cr);
		for(CartItem crItem : cr.getCartItems()) {
			crItem.setAmount((crItem.getProduct().getPrice())*(crItem.getQuantity()));
			int modprice=calculatePricing(crItem.getProduct(), crItem.getQuantity());
			crItem.setAmountRevised(modprice);
			crItem.setCart(cr);
			cartItemRep.save(crItem);
		}
		
		//Update total price
		reCalcTotal(cr);
		return cr;
	}
	
	public void reCalcTotal(Cart cr) {
		List<CartItem> cartItemList=cartItemRep.findByCart(cr);
		int totalRev=0;
		int total=0;
		for(CartItem crItem : cartItemList) {
			int crItemAmtRev=0;
			int crItemAmt=0;
			crItemAmtRev=crItem.getAmountRevised();
			crItemAmt=crItem.getAmount();
			totalRev=totalRev+crItemAmtRev;
			total= total+crItemAmt;
		}
		cr.setPriceTotal(total);
		cr.setSavings(total-totalRev);
		cartRep.save(cr);
	}
	
	public int calculatePricing(Product pr, int quantity) {
		int modprice=0;
		List<Offer> offerList=offerRep.findByProductOrderByPriorityOrderAsc(pr);
		for(Offer off : offerList) {
			PricingPolicy pricingPolicy=null;
	       if(off.getOfferCode().startsWith("GROUP")){
	    	  pricingPolicy= new GroupPricing(pr, off.getPercentOff());
	    	  modprice=pricingPolicy.priceForQuantity(quantity);
	    	  break;
	       }
	       else if(off.getOfferCode().startsWith("PROMO")){
	    	   pricingPolicy= new PromotionPricing(pr, off.getPercentOff());
	    	   modprice=pricingPolicy.priceForQuantity(quantity);
	    	   break;
	       }
	       else if(off.getOfferCode().startsWith("BUNDLE")){
	    	   pricingPolicy= new BundlePricing(pr, off.getBundleSize(), off.getPercentOff());
	    	   modprice=pricingPolicy.priceForQuantity(quantity);
	    	   break;
	       }
	       else if(off.getOfferCode().startsWith("DEG")){
	    	   pricingPolicy= new DegressivePricing(pr, off.getThresholdQuan(), off.getReducedUnitPrice());
	    	   modprice=pricingPolicy.priceForQuantity(quantity);
	    	   int unitprice=pr.getPrice();
	    	   if((unitprice*quantity)>modprice)
	    		   break;
	    	   else
	    		   continue;
	       }
		}
		return modprice;
}
}
