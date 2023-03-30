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
            String SQL = "SELECT wishlist_id, wishlistName FROM wishlist;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                int listID = rs.getInt("wishlist_id");
                String listName = rs.getString("wishlistName");

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
            String SQL = "SELECT wish_id, wishName, price, description, link FROM wishes INNER JOIN wishlist_wishes USING(wish_id) WHERE wishlist_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, wishlistID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int wishID = rs.getInt("wish_id");
                String itemName = rs.getString("wishName");
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


    //Add Wishlist
    public void addWishlist(WishlistDTO wishlist) {
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            //ID
            int wishlistID = 0;

            //find wishlist_ID
            String SQL1 = "SELECT wishlist_ID from wishlist where wishlistName = ?;";
            PreparedStatement pstmt = conn.prepareStatement(SQL1);
            pstmt.setString(1, wishlist.getListName());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                wishlistID = rs.getInt("wishlists_ID");
            }

            //insert wishlist to
            String SQL = "INSERT INTO wishlist (wishlist_id, wishName) " +
                    "VALUES (?, ?)";
            pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, wishlistID);
            pstmt.setString(2, wishlist.getListName());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                wishlistID = rs.getInt(1);
                Wishlist list = new Wishlist(wishlist.getWishlistID(), wishlist.getListName());
                list.setWishlistID(wishlistID);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //Add wish
    public void addWish(WishDTO wish) {
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            int wishID = 0;

            //find wish_id
            String SQL1 = "SELECT wish_id from wishes where wishName = ?;";
            PreparedStatement pstmt = conn.prepareStatement(SQL1);
            pstmt.setString(1, wish.getItemName());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                wishID = rs.getInt("wish_ID");
            }

            //insert wish to wishes
            String SQL = "INSERT INTO wishes (wish_id, listName, price, description, link) " +
                    "VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, wishID);
            pstmt.setString(2, wish.getItemName());
            pstmt.setDouble(3, wish.getPrice());
            pstmt.setString(4, wish.getDescription());
            pstmt.setString(5, wish.getLink());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                wishID = rs.getInt(1);
                Wish list = new Wish(wish.getWishID(), wish.getItemName(), wish.getPrice(), wish.getDescription(), wish.getLink());
                list.setWishID(wishID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
