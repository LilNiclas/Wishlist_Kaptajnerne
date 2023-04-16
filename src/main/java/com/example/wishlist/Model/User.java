package com.example.wishlist.Model;

public class User {
    private String email;
    private String username;
    private int usersId;

    public User(String email, String username, int userId) {
        this.email = email;
        this.username = username;
        this.usersId = userId;
    }

    public int getUserId() {
        return usersId;
    }
    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
