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
//        if(!cartItems.contains(item)){
//            cartItems.add(item);
//        }
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

    public void deleteCart(ItemPOJO itemPOJO) {
        cartItems.remove(itemPOJO);
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