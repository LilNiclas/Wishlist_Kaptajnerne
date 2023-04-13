package com.example.wishlist.Repository;

import com.example.wishlist.DTO.WishDTO;
import com.example.wishlist.DTO.WishlistDTO;
import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Model.Wishlist;

import java.util.List;

public interface IRepository {


    Wishlist findWishlistByID(int wishlistID);

    //View Wishlists
    public List<Wishlist> getWishlists(String email);

    //View Wishes
    public List<Wish> getWishes(int listID);

    //Add Wishlist
    public void addWishlist(WishlistDTO wishlist);

    //Add Wish
    public void addWish(WishDTO wish, int wishlistID);


    //View Users
    public List<User> getUsers();

    //Delete Wishlist
    public void deleteWishlist(Integer wishlistID);

    //Edit Wish
    public void editWish(Wish wish, int wishlistID);

    //Edit Wish
    public void editWish(Wish wish);

    public void deleteWish (int id);
}
