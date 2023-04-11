package com.example.wishlist.DTO;

public class WishDTO {
    private int wishID;
    private String itemName;
    private double price;
    private String description;
    private String link;
    private int wishlistID;

    public WishDTO(int wishID, String itemName, double price, String description, String link, int  wishlistID) {
        this.wishID = wishID;
        this.itemName = itemName;
        this.price = price;
        this.description = description;
        this.link = link;
        this.wishlistID = wishlistID;
    }
    public WishDTO(int wishID, String itemName, double price, String description, String link) {
        this.wishID = wishID;
        this.itemName = itemName;
        this.price = price;
        this.description = description;
        this.link = link;
    }



    public WishDTO () {
    }

    public int getWishID() {
        return wishID;
    }

    public void setWishID(int wishID) {
        this.wishID = wishID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public int getWishlistID() {
        return wishlistID;
    }

    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }
}
