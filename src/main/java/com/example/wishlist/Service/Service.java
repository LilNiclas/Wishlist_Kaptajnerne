package com.example.wishlist.Service;


import com.example.wishlist.DTO.WishlistDTO;
import com.example.wishlist.Repository.Repository;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private Repository repository;

    public Service (Repository repository) {
        this.repository = repository;
    }

    public List<WishlistDTO> getWishlists() {
        return repository.getWishlists();
    }


}
