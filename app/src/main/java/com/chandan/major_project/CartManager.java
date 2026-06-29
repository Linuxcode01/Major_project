package com.chandan.major_project;

import java.util.ArrayList;

public class CartManager {
    private static CartManager instance;
    private ArrayList<ItemPOJO> cartItems = new ArrayList<>();

    private CartManager() {}

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addItem(ItemPOJO item) {
        cartItems.add(item);
    }

    public ArrayList<ItemPOJO> getCartItems() {
        return cartItems;
    }

    public int getTotalPrice() {
        int total = 0;
        for (ItemPOJO item : cartItems) {
            total += Integer.parseInt(item.getProductPrice());
        }
        return total;
    }

    public void clearCart() {
        cartItems.clear();
    }
}