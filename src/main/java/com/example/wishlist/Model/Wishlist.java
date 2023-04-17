package com.example.wishlist.Model;

public class Wishlist {

    private int wishlistID;
    private String listName;
    private String username;
    private int users_id;

    public Wishlist(int wishlistID, String listName, String username) {
        this.wishlistID = wishlistID;
        this.listName = listName;
        this.username = username;
    }
    public Wishlist(int wishlistID, String listName, String username, int users_id) {
        this.wishlistID = wishlistID;
        this.listName = listName;
        this.username = username;
        this.users_id = users_id;
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
