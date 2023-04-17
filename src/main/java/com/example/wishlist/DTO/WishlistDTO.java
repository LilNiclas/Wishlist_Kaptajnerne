package com.example.wishlist.DTO;

public class WishlistDTO {

    private int wishlistID;
    private String listName;
    private int users_id;

    public WishlistDTO(int wishlistID, String listName, int users_id) {
        this.wishlistID = wishlistID;
        this.listName = listName;
        this.users_id = users_id;
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

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }
}
