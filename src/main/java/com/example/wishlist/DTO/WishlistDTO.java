package com.example.wishlist.DTO;

public class WishlistDTO {

    private int wishlistID;
    private String listName;

    public WishlistDTO(int wishlistID, String listName) {
        this.wishlistID = wishlistID;
        this.listName = listName;
    }

    public WishlistDTO() {
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
