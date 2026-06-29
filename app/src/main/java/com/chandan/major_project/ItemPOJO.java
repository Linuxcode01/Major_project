package com.chandan.major_project;

public class ItemPOJO {
    private int ProductImage;
    private String ProductName;
    private String ProductPrice;
    private int quantity;

    public ItemPOJO(int productImage, String productName, String productPrice) {
        ProductImage = productImage;
        ProductName = productName;
        ProductPrice = productPrice;
        this.quantity = 1;
    }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void incrementQuantity() { this.quantity++; }
    public void decrementQuantity() { if (this.quantity > 0) this.quantity--; }


    public int getProductImage() {
        return ProductImage;
    }


    public String getProductName() {
        return ProductName;
    }


    public String getProductPrice() {
        return ProductPrice;
    }

}
