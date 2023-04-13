package com.example.wishlist.Service;

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
    public List<Wishlist> getWishlists(String email) {
        return repositoryDB.getWishlists(email);
    }

    //View Wishes
    public List<Wish> getWishes(int wishlistID) {
        return repositoryDB.getWishes(wishlistID);
    }


    //Add Wishlist
    public void addWishlist(WishlistDTO wishlist) {
        repositoryDB.addWishlist(wishlist);
    }

    //Add Wish
    public void addWish(WishDTO wish, int wishlistID) {
        repositoryDB.addWish(wish, wishlistID);
    }

    //Find Wishlist
    public Wishlist findWishlistByID (int wishlistID) {
        return repositoryDB.findWishlistByID(wishlistID);
    }

    //Delete Wishlist
    public void deleteWishlist (Integer wishlistID) {
        repositoryDB.deleteWishlist(wishlistID);
    }

    //View Users
    public List<User> getUsers() {
        return repositoryDB.getUsers();
    }

    //Edit Wish
    public void editWish(Wish wish, int wishlistID) {
        repositoryDB.editWish(wish, wishlistID);
    }

    //Edit Wish
    public void editWish(Wish wish){
    }

    public void deleteWish (int id){
        repositoryDB.deleteWish(id);
    }
}
