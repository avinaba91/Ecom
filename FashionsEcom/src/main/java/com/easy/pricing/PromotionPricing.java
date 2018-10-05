package com.easy.pricing;

import com.easy.model.Item;
import com.easy.model.Product;

public class PromotionPricing extends PricingPolicy {

    private final double priceFactor;

    public PromotionPricing(Product pr, int percentPromotion) {
        super(pr);
        if (percentPromotion < 0 || percentPromotion > 100 ) {
            throw new IllegalArgumentException("percentPromotion must be in [0,100]");
        }
        this.priceFactor = (100 - percentPromotion) / 100.0;
    }

    @Override
    public int priceForQuantity(int quantity) {
        return (int) (super.priceForQuantity(quantity) * priceFactor);
    }
}
