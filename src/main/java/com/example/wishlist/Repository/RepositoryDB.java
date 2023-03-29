package com.example.wishlist.Repository;

import com.example.wishlist.DTO.WishDTO;
import com.example.wishlist.DTO.WishlistDTO;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Model.Wishlist;
import com.example.wishlist.Util.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository("wishlists")
public class RepositoryDB implements IRepository {


    @Value("${spring.datasource.url}")
    private String db_url;

    @Value("${spring.datasource.username}")
    private String uid;

    @Value("${spring.datasource.password}")
    private String pwd;


    //View Wishlists
    @Override
    public List<Wishlist> getWishlists() {
        List<Wishlist> wishlists = new ArrayList<>();

        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "SELECT wishlist_id, name FROM wishlist;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                int listID = rs.getInt("wishlist_id");
                String listName = rs.getString("name");

                wishlists.add(new Wishlist(listID, listName));
            }
            return wishlists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //View Wishes
    public List<Wish> getWishes(int wishlistID) {
        List<Wish> wishes = new ArrayList<>();
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "SELECT wish_id, name, price, description, link FROM wishes INNER JOIN wishlist_wishes USING(wish_id) WHERE wishlist_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, wishlistID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int wishID = rs.getInt("wish_id");
                String itemName = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String link = rs.getString("link");

                wishes.add(new Wish(wishID, itemName, price, description, link));
            }
            return wishes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void addWishlist(WishlistDTO wishlistDTO) {
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "INSERT INTO wishlist (wishlist_id, name) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, wishlistDTO.getWishlistID());
            pstmt.setString(2, wishlistDTO.getListName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void addWish(WishDTO wishDTO) {
        try {
        Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = " ";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
          //  pstmt.setInt(1, wishDTO);
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
