package com.example.wishlist.Repository;

import com.example.wishlist.DTO.WishlistDTO;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Model.Wishlist;

import java.util.List;

public interface IRepository {


    //View Wishlists
    public List<Wishlist> getWishlists();

    //View Wishes
    public List<Wish> getWishes(int listID);

    //Add Wishlist
    public void addWishlist (WishlistDTO wishlistDTO);


}
