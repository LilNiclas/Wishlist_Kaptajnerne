package com.example.wishlist.Repository;

import com.example.wishlist.DTO.WishDTO;
import com.example.wishlist.DTO.WishlistDTO;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Model.Wishlist;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("Repository")
public class Repository_Stub implements  IRepository {

    @Override
    public Superhero findSuperheroByID(int heroID) {
        for (Superhero superhero : superheroes) {
            if (superhero.getHeroID() == (heroID)) {
                return superhero;
            }
        }
        return null;
    }

    @Override
    public List<Wishlist> getWishlists() {
        return null;
    }

    @Override
    public List<Wish> getWishes(int listID) {
        return null;
    }

    @Override
    public void addWishlist(WishlistDTO wishlist) {

    }

    @Override
    public void addWish(WishDTO wish, int wishlistID) {

    }

    @Override
    public void deleteWishlist(Integer wishlistID) {

    }
}
