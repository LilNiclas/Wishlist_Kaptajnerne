package com.example.wishlist.Service;

import com.example.wishlist.DTO.UserDTO;
import com.example.wishlist.DTO.WishDTO;
import com.example.wishlist.DTO.WishlistDTO;
import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Model.Wishlist;
import com.example.wishlist.Repository.RepositoryDB;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private RepositoryDB repositoryDB;

    public Service(RepositoryDB repositoryDB) {
        this.repositoryDB = repositoryDB;
    }

    //View Wishlists
    public List<Wishlist> getWishlists(String username) {
        return repositoryDB.getWishlists(username);
    }

    //View Wishes
    public List<Wish> getWishes(int wishlistID) {
        return repositoryDB.getWishes(wishlistID);
    }

    //View Users
    public List<User> getUsers() {
        return repositoryDB.getUsers();
    }


    //Add Wishlist
    public void addWishlist(WishlistDTO wishlist, String username) {
        repositoryDB.addWishlist(wishlist, username);
    }

    //Add Wish
    public void addWish(WishDTO wish, int wishlistID) {
        repositoryDB.addWish(wish, wishlistID);
    }

    public void addUser(UserDTO user) {
        repositoryDB.addUser(user);
    }


    //Find Wishlist
    public Wishlist findWishlistByID(int wishlistID) {
        return repositoryDB.findWishlistByID(wishlistID);
    }

    //Delete Wishlist
    public void deleteWishlist(Integer wishlistID) {
        repositoryDB.deleteWishlist(wishlistID);
    }

    //Edit Wish
    public void editWish(Wish wish, int wishlistID) {
        repositoryDB.editWish(wish, wishlistID);
    }

    //Edit Wish
    public void editWish(Wish wish) {
    }

    public User getEmail() {
        return getEmail();
    }

    public void deleteWish(int id) {
        repositoryDB.deleteWish(id);
    }

    public int findWishlistId(int id) {
        return repositoryDB.findWishlistId(id);
    }

    //View Wish2
    public Wish getWishes2(int wishlistID) {
        return repositoryDB.getWishes2(wishlistID);
    }

}

