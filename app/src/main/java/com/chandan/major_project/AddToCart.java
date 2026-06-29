//package com.chandan.major_project;
//
//import java.util.ArrayList;
//
//public class AddToCart implements ItemAdapter.OnAddToCartListener {
//
//    static ArrayList<ItemPOJO> addedToCart = new ArrayList<>();
//
//    @Override
//    public void onAddToCart(ItemPOJO item) {
//        addedToCart.add(new ItemPOJO(item.getProductImage(),item.getProductName(),item.getProductPrice()));
//    }
//
//
//    public static void main(String[] args) {
//        for (ItemPOJO itemPOJO : addedToCart) {
//            System.out.println(itemPOJO.getProductName());
//        }
//    }
//}
