package com.example.wishlist.Model;

public class Wishlist {

    private int wishlistID;
    private String name;

    public Wishlist(int wishlistID, String name) {
        this.wishlistID = wishlistID;
        this.name = name;
    }

    public int getWishlistID() {
        return wishlistID;
    }

    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
