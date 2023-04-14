package com.example.wishlist.Repository;

import com.example.wishlist.DTO.UserDTO;
import com.example.wishlist.DTO.WishDTO;
import com.example.wishlist.DTO.WishlistDTO;
import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Model.Wishlist;
import com.example.wishlist.Util.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository("wishlists")
public class RepositoryDB implements IRepository {


    @Value("jdbc:mysql://localhost:3306/wishlist")
    private String db_url;

    @Value("Kjartan")
    private String uid;

    @Value("1234")
    private String pwd;


    //View Wishlists
    @Override
    public List<Wishlist> getWishlists(String username) {
        List<Wishlist> wishlists = new ArrayList<>();

        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "SELECT wishlist_id, wishlistName, username FROM wishlist INNER JOIN users USING(username) Where username = ? ;";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int listID = rs.getInt("wishlist_id");
                String listName = rs.getString("wishlistName");
                String username1 = rs.getString("username");

                wishlists.add(new Wishlist(listID, listName, username));
            }
            return wishlists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //View Wishes
    @Override
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

    //View Users
    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "SELECT username, email FROM users;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                users.add(new User(username, email));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Add User
    public void addUser(UserDTO user) {
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);

            int userID = 0;

            //insert user to users
            String SQL = "INSERT INTO users (username, email) " +
                    "VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                userID = rs.getInt(1);
                UserDTO userDTO = new UserDTO(user.getEmail(), user.getUsername(), user.getUserID());
                userDTO.setUserID(userID);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Add Wishlist
    @Override
    public void addWishlist(WishlistDTO wishlist, String username) {
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);

            // insert wishlist to wishlist table with the corresponding username
            String SQL = "INSERT INTO wishlist (wishlistName, username) " +
                    "VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, wishlist.getListName());
            ps.setString(2, username);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int wishlistID = rs.getInt(1);
                WishlistDTO wishlists = new WishlistDTO(wishlist.getWishlistID(), wishlist.getListName(), wishlist.getUsername());
                wishlists.setWishlistID(wishlistID);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    //Add wish
    @Override
    public void addWish(WishDTO wish, int wishlistID) {
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            //ID
            int wishID = 0;

            //insert wish to wishes
            String SQL = "INSERT INTO wishes (wish_id, wishName, price, description, link) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, wishID);
            pstmt.setString(2, wish.getItemName());
            pstmt.setDouble(3, wish.getPrice());
            pstmt.setString(4, wish.getDescription());
            pstmt.setString(5, wish.getLink());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();

            //wish object
            if (rs.next()) {
                wishID = rs.getInt(1);
                //Insert into wishlist_wishses
                WishDTO list = new WishDTO(wish.getWishID(), wish.getItemName(), wish.getPrice(), wish.getDescription(), wish.getLink());
                SQL = "INSERT INTO wishlist_wishes (wishlist_id, wish_id) " +
                        "VALUES (?, ?)";
                pstmt = conn.prepareStatement(SQL);
                pstmt.setInt(1, wishlistID);
                pstmt.setInt(2, wishID);
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Delete Wishlist
    @Override
    public void deleteWishlist(Integer wishlistID) {
        String SQL = "DELETE FROM wishlist WHERE wishlist_id = ?";
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, wishlistID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Wishlist with ID " + wishlistID + " has been deleted.");
            } else {
                System.out.println("No wishlist with ID " + wishlistID + " found to delete.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Find wishlist by ID
    @Override
    public Wishlist findWishlistByID(int wishlistID) {
        String SQL = "SELECT * FROM wishlist WHERE wishlist_id = ?;";

        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, wishlistID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int wishlistId = rs.getInt("wishlist_id");
                String listName = rs.getString("wishlistName");
                String username = rs.getString("username");
                return new Wishlist(wishlistId, listName, username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //Delete Wish

    /*@Override
    public void deleteWish(int wishlistID, WishDTO wish) {
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            int wishID = 0;

            String SQL = "DELETE FROM wishlist_wishes \n" +
                    "WHERE wishlist_id = (SELECT wishlist_id FROM wishlist WHERE wishlistName = '?' AND username = '?')\n" +
                    "AND wish_id = (SELECT wish_id FROM wishes WHERE wishName = '?');\n";

            PreparedStatement stmt1 = conn.prepareStatement(SQL);
            stmt1.setInt(1, wishlistID);
        }
    }*/


    //Edit Wish
    @Override
    public void editWish(Wish wish) {
    }

    //@Override
    public void deleteWish(int id) {

    }

    //Edit Wish
    @Override
    public void editWish(Wish wish, int wishlistID) {
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "UPDATE wishes SET wishName = ?, price = ?, description = ?, link = ? WHERE wish_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
                stmt.setString(1, wish.getItemName());
                stmt.setDouble(2, wish.getPrice());
                stmt.setString(3, wish.getDescription());
                stmt.setString(4, wish.getLink());
                stmt.setInt(5, wishlistID);
                // stmt.setInt(6, wish.getWishID());
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public int findWishlistId(int wishId) {
        int wishlistId = 0;
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "SELECT wishlist_id FROM wishlist_wishes WHERE WISH_ID = ?";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1, wishId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                wishlistId = rs.getInt("WISHLIST_ID");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return wishlistId;
    }

    //View Wish2
    @Override
    public Wish getWishes2(int id) {
        System.out.println(id);
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "SELECT wish_id, wishName, price, description, link FROM wishes WHERE wish_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            Wish wish1=null;
            while (rs.next()) {
                int wishID = rs.getInt("wish_id");
                String wishName = rs.getString("wishName");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String link = rs.getString("link");
                wish1 = new Wish(wishID, wishName, price, description, link);
            }

            return wish1;

        } catch (SQLException ex) {
            return null;
        }
    }

    //View Wishes2
    public List<Wish>getWishList(int id) {
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "SELECT wish_id, wishName, price, description, link FROM wishes INNER JOIN wishlist_wishes USING(wish_id) WHERE wishlist_id = ?";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<Wish> list = new ArrayList<>();
            if (rs.next()) {
                int wishId = rs.getInt("WISH_ID");
                String wishName = rs.getString("WISHNAME");
                Double price = rs.getDouble("price");
                String Description = rs.getString("DESCRIPTION");
                String link = rs.getString("link");
                Wish wish1 = new Wish(wishId, wishName, price, Description, link);
                list.add(wish1);
            }

            return list;

        } catch (SQLException ex) {
            return null;
        }
    }



}
