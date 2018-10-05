package com.easy.pricing;

import java.util.List;

import com.easy.model.Item;
import com.easy.model.Product;
	
public class GroupPricing extends PricingPolicy{
    private final int priceFactor;

    public GroupPricing(Product pr, int reducedPercentage) {
        super(pr);
        if (reducedPercentage < 0 || reducedPercentage > 100 ) {
            throw new IllegalArgumentException("reducedPercentage must be in [0,100]");
        }
        this.priceFactor = (100 - reducedPercentage) / 100;
    }

    @Override
    public int priceForQuantity(int quantity) {
    	return super.priceForQuantity(quantity) *priceFactor;  
    }
}
