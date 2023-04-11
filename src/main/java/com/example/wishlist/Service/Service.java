package com.example.wishlist.Service;

import com.example.wishlist.DTO.WishDTO;
import com.example.wishlist.DTO.WishlistDTO;
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
    public List<Wishlist> getWishlists() {
        return repositoryDB.getWishlists();
    }

    //View Wishes
    public List<Wish> getWishes(int wishlistID) {
        return repositoryDB.getWishes(wishlistID);
    }


    //Add Wishlist
    public void addWishlist(WishlistDTO wishlist) {
        repositoryDB.addWishlist(wishlist);
    }


    public void addWish(WishDTO wish, int wishlistID) {
        repositoryDB.addWish(wish, wishlistID);
    }

}
