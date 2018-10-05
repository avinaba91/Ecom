package com.easy.pricing;

import com.easy.model.Item;
import com.easy.model.Product;

public class BundlePricing extends PricingPolicy {
	private final int bundleSize;
    private final int priceFactor;

    public BundlePricing(Product pr, int bundleSize, int reducedPercentage) {
        super(pr);
        this.bundleSize = bundleSize;
        if (reducedPercentage < 0 || reducedPercentage > 100 ) {
            throw new IllegalArgumentException("reducedPercentage must be in [0,100]");
        }
        this.priceFactor = (100 - reducedPercentage) / 100;
    }

    @Override
    public int priceForQuantity(int howManybundles) {
    	return (howManybundles*bundleSize) * super.price() *priceFactor;  
    }
}
