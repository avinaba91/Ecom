package com.easy.pricing;

import com.easy.model.Item;
import com.easy.model.Product;

public class DegressivePricing extends PricingPolicy {
    private final int thresholdQuantity;
    private final int reducedUnitPrice;

    public DegressivePricing(Product pr, int thresholdQuantity, int reducedUnitPrice) {
        super(pr);
        this.thresholdQuantity = thresholdQuantity;
        this.reducedUnitPrice = reducedUnitPrice;
    }

    @Override
    public int priceForQuantity(int quantity) {
        if (quantity < thresholdQuantity) {
            return super.priceForQuantity(quantity);
        } else {
            return (super.price()-reducedUnitPrice) * quantity;
        }
    }
}
