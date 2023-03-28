package com.example.wishlist.Repository;

import com.example.wishlist.DTO.WishlistDTO;

import java.util.List;

public interface IRepository {


    //View wishlists
    public List<WishlistDTO> getWishlists();


}
