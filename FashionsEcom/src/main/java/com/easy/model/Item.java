package com.easy.model;

public interface Item {
    int price();
    int priceForQuantity(int quantity);
    String description();
}
