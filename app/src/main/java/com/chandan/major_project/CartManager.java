package com.chandan.major_project;

import java.util.ArrayList;
import java.util.Objects;

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
        for (ItemPOJO existing : cartItems) {
            if (existing.getProductName().equals(item.getProductName())) {
                existing.incrementQuantity();
                return;
            }
        }
        item.setQuantity(1);
        cartItems.add(item);
    }

    public ArrayList<ItemPOJO> getCartItems() {
        return cartItems;
    }

    public int getTotalPrice() {
        int total = 0;
        for (ItemPOJO item : cartItems) {
            total += Integer.parseInt(item.getProductPrice()) * item.getQuantity();
        }
        return total;
    }

    public void removeItem(int position) {
        if (position < 0 || position >= cartItems.size()) return;
        ItemPOJO item = cartItems.get(position);
        if (item.getQuantity() > 1) {
            item.decrementQuantity();
        } else {
            cartItems.remove(position);
        }
    }

    public int getQuantity(){
        int total = 0;
        for (int i = 0; i < cartItems.size(); i++){
            for (int j = 1; j < cartItems.size(); j++){
                if(Objects.equals(cartItems.get(i), cartItems.get(j))){
                    total++;
                }
            }
        }
        return total;
    }
}