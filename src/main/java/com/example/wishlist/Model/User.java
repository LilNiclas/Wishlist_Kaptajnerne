package com.example.wishlist.Model;

public class User {
    private String email;
    private String username;

    public User(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public User() {

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
