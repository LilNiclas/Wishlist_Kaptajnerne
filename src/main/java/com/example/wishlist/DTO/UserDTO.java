package com.example.wishlist.DTO;

public class UserDTO {
    private int userID;
    private String email;
    private String username;

    public UserDTO(String email, String username, int userID) {
        this.email = email;
        this.username = username;
        this.userID = userID;
    }
    public UserDTO(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public UserDTO() {
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

    public int getUserID() {
        return userID;
    }

    public void  setUserID(int userID) {
        this.userID = userID;
    }
}
