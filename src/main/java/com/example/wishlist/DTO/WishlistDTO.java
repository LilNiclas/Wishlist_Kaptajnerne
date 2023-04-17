package com.example.wishlist.DTO;

public class WishlistDTO {

    private int wishlistID;
    private String listName;
    private String username;
    private int users_id;

    public WishlistDTO(int wishlistID, String listName, String username) {
        this.wishlistID = wishlistID;
        this.listName = listName;
        this.username = username;
    }
    public WishlistDTO(int wishlistID, String listName, String username, int users_id) {
        this.wishlistID = wishlistID;
        this.listName = listName;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }
}
