package com.example.wishlist.DTO;

public class WishlistDTO {

    private int wishlistID;
    private String listName;
    private String username;

    public WishlistDTO(int wishlistID, String listName, String username) {
        this.wishlistID = wishlistID;
        this.listName = listName;
        this.username = username;
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

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
