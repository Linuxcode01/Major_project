package com.chandan.major_project;

import java.util.ArrayList;

public class ProductManager {
    private  static  ProductManager instance;

    private ArrayList<ItemPOJO> productItem = new ArrayList<>();

    private  ProductManager(){}

    public static ProductManager getInstance(){
        if (instance == null){
            instance = new ProductManager();
        }
        return instance;
    }

    public void addItem(ItemPOJO itemPOJO){
        productItem.add(itemPOJO);
    }

    public ArrayList<ItemPOJO> getProductItem(){
        return productItem;
    }
}
