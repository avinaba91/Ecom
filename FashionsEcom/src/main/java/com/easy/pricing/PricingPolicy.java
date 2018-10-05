package com.easy.pricing;

import com.easy.model.Item;
import com.easy.model.Product;

public class PricingPolicy extends Product {

    private final Product product;

    public PricingPolicy(Product product) {
        this.product = product;
    }

    public int price() { return product.price(); }

    public String description() { return product.description(); }

    public int priceForQuantity(int quantity) {
        return product.priceForQuantity(quantity);
    }


}
