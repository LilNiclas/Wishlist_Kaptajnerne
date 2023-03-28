package com.example.wishlist.Model;

public class Wishlist {

    private int wishlistID;
    private String listName;

    public Wishlist(int wishlistID, String listName) {
        this.wishlistID = wishlistID;
        this.listName = listName;
    }

    public int getWishlistID() {
        return wishlistID;
    }

    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String name) {
        this.listName = name;
    }
}
