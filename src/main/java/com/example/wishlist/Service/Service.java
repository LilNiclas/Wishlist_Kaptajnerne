package com.example.wishlist.Service;


import com.example.wishlist.DTO.WishlistDTO;
import com.example.wishlist.Repository.RepositoryDB;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private RepositoryDB repositoryDB;

    public Service (RepositoryDB repositoryDB) {
        this.repositoryDB = repositoryDB;
    }

    public List<WishlistDTO> getWishlists() {
        return repositoryDB.getWishlists();
    }


}
