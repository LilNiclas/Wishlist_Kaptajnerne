package com.example.wishlist.Service;

import com.example.wishlist.DTO.UserDTO;
import com.example.wishlist.DTO.WishDTO;
import com.example.wishlist.DTO.WishlistDTO;
import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Model.Wishlist;
import com.example.wishlist.Repository.RepositoryDB;

import javax.security.auth.login.LoginException;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private RepositoryDB repositoryDB;

    private Wishlist wishlist;

    public Service(RepositoryDB repositoryDB) {
        this.repositoryDB = repositoryDB;
    }

    //View Wishlists
    public List<Wishlist> getWishlists(int userID) {
        return repositoryDB.getWishlists(userID);
    }


    //View Wishes
    public List<Wish> getWishes(int wishlistID) {
        return repositoryDB.getWishes(wishlistID);
    }

    //View Users
    public List<User> getUsers() {
        return repositoryDB.getUsers();
    }

    //Add User
    public void addUser(UserDTO user) {
        repositoryDB.addUser(user);
    }

    //find userID by listID
    public int findUserIDByWishlistID(int wishlistID) {
        return repositoryDB.findUserIDByWishlistID(wishlistID);
    }

    //Add Wishlist
    public void addWishlist(WishlistDTO wishlist, int userID) {
        repositoryDB.addWishlist(wishlist, userID);
    }


    //Add Wish
    public void addWish(WishDTO wish, int wishlistID) {
        repositoryDB.addWish(wish, wishlistID);
    }


    //Delete Wishlist
    public void deleteWishlist(Integer wishlistID) {
        repositoryDB.deleteWishlist(wishlistID);
    }

    //done
    //_________________________________________________________________________
    //not done


    //Edit Wish
    public void editWish(Wish wish, int wishID) {
        repositoryDB.editWish(wish, wishID);
    }

    //Edit Wish
    public void editWish(Wish wish) {
    }




    //Find Wishlist
    public Wishlist findWishlistByID(int wishlistID) {
        return repositoryDB.findWishlistByID(wishlistID);
    }


    //Edit user
    public void editUser(User user)  {
        repositoryDB.editUser(user);
    }

    //Get User
    public User getUserFromId(int id){
        return repositoryDB.getUserFromId(id);
    }

    //Get Email
    public User getEmail() {
        return getEmail();
    }

    //DeleteWish
    public void deleteWish(int id) {
        repositoryDB.deleteWish(id);
    }

    //Delete User
    public void deleteUser(int userID) {
        repositoryDB.deleteUser(userID);
    }

    //Find wish by id
    public Wish findWishByID(int id){
        return repositoryDB.findWishByID(id);
    }

    //Find wishlistID
    public int findWishlistId(int wishID) {
        return repositoryDB.findWishlistId(wishID);
    }

    //View Wish2
    public Wish getWish2(int wishlistID) {
        return repositoryDB.getWish2(wishlistID);
    }

    //
    public int getWishlistID(){
        return wishlist.getWishlistID();
    }


}

