package com.example.wishlist.Repository;

import com.example.wishlist.DTO.UserDTO;
import com.example.wishlist.DTO.WishDTO;
import com.example.wishlist.DTO.WishlistDTO;
import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Model.Wishlist;
import com.example.wishlist.Util.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;

import javax.security.auth.login.LoginException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository("wishlists")
public class RepositoryDB implements IRepository {


    @Value("${jdbc:mysql://localhost:3306/wishlist2}")
    private String db_url;

    @Value("${root}")
    private String uid;

    @Value("${1234}")
    private String pwd;


    //View Wishlists
    @Override
    public List<Wishlist> getWishlists(int userID) {
        List<Wishlist> wishlists = new ArrayList<>();

        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "SELECT wishlist_id, wishlistName, users_id FROM wishlist INNER JOIN users USING(users_id) Where users_id = ? ;";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int listID = rs.getInt("wishlist_id");
                String listName = rs.getString("wishlistName");
                String users_id = rs.getString("users_id");

                wishlists.add(new Wishlist(listID, listName, userID));
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
            String SQL = "SELECT wish_id, wishName, price, description, link, wishlist_id FROM wishes WHERE wishlist_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, wishlistID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int wishID = rs.getInt("wish_id");
                String wishName = rs.getString("wishName");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String link = rs.getString("link");

                wishes.add(new Wish(wishID, wishName, price, description, link, wishlistID));
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
            String SQL = "SELECT username, users_id, email FROM users;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                int usersID = rs.getInt("USERS_ID");
                String username = rs.getString("username");
                String email = rs.getString("email");
                users.add(new User(username, email, usersID));
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

    //find userID by listID
    public int findUserIDByWishlistID(int wishlistID) {
        int userID = 0;
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "SELECT users_id FROM wishlist WHERE wishlist_id=? ";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1, wishlistID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                userID = rs.getInt("users_id");
            }
            return userID;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }


    //Add Wishlist
    @Override
    public void addWishlist(WishlistDTO wishlist, int userID) {
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);

            // insert wishlist to wishlist table
            String SQL = "INSERT INTO wishlist (wishlistName, users_id) " +
                    "VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, wishlist.getListName());
            ps.setInt(2, userID);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int wishlistID = rs.getInt(1);
                WishlistDTO wishlists = new WishlistDTO(wishlist.getWishlistID(), wishlist.getListName(), wishlist.getUsers_id());
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
            String SQL = "INSERT INTO wishes (wishName, price, description, link, wishlist_id) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, wish.getWishName());
            pstmt.setDouble(2, wish.getPrice());
            pstmt.setString(3, wish.getDescription());
            pstmt.setString(4, wish.getLink());
            pstmt.setInt(5, wishlistID);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                wishID = rs.getInt(1);
                WishDTO wishes = new WishDTO(wish.getWishID(), wish.getWishName(), wish.getPrice(), wish.getDescription(), wish.getLink(), wish.getWishlistID());
                wishes.setWishID(wishID);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Delete Wishlist
    @Override
    public void deleteWishlist(Integer wishlistID) {
        String SQL1 = "DELETE FROM wishes WHERE wishlist_id = ?";
        String SQL2 = "DELETE FROM wishlist WHERE wishlist_id = ?";
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            conn.setAutoCommit(false);
            PreparedStatement pstmt1 = conn.prepareStatement(SQL1, Statement.RETURN_GENERATED_KEYS);
            pstmt1.setInt(1, wishlistID);
            int wishesDeleted = pstmt1.executeUpdate();
            PreparedStatement pstmt2 = conn.prepareStatement(SQL2, Statement.RETURN_GENERATED_KEYS);
            pstmt2.setInt(1, wishlistID);
            int wishlistDeleted = pstmt2.executeUpdate();
            if (wishlistDeleted > 0) {
                conn.commit(); // commit the transaction if both statements succeed
                System.out.println("Wishlist with ID " + wishlistID + " has been deleted, along with " + wishesDeleted + " wishes.");
            } else {
                conn.rollback(); // rollback the transaction if the wishlist was not found to delete
                System.out.println("No wishlist with ID " + wishlistID + " found to delete.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Find listID by wishID
    public int findWishlistId(int wishId) {
        int wishlistId = 0;
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "SELECT wishlist_id FROM wishes WHERE wish_id = ?";
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

    //Edit Wish
    @Override
    public void editWish(Wish wish, int wishID) {
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "UPDATE wishes SET wishName = ?, price = ?, description = ?, link = ? WHERE wish_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
                stmt.setString(1, wish.getWishName());
                stmt.setDouble(2, wish.getPrice());
                stmt.setString(3, wish.getDescription());
                stmt.setString(4, wish.getLink());
                stmt.setInt(5, wishID);

                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    //View Wish2
    @Override
    public Wish getWish2(int wishID) {
        Wish wish = null;
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "SELECT wish_id, wishName, price, description, link FROM wishes WHERE wish_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, wishID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String wishName = rs.getString("wishName");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String link = rs.getString("link");
                wish = new Wish(wishID, wishName, price, description, link);
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
        return wish;
    }

    //Delete Wish
    @Override
    public void deleteWish(int wish_id) {
        String SQL2 = "delete from wishes where wish_id = ?";
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            PreparedStatement pstmt = conn.prepareStatement(SQL2, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, wish_id);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }


    //Edit User
    public void editUser(User user) {
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "UPDATE users SET username = ?, email = ? WHERE users_id = ?;";
            try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
                stmt.setString(1, (user.getUsername()));
                stmt.setString(2, (user.getEmail()));
                stmt.setInt(3, user.getUserId());

                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    //User from userID
    @Override
    public User getUserFromId(int userID) {
        User user = null;
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "SELECT * FROM users WHERE users_id = ?;";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                user = new User(username, email, userID);
            }
            return user;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }


    //Find wish by ID
    @Override
    public Wish findWishByID(int wishID) {
        Wish wish = null;
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "SELECT * FROM wishes WHERE wish_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, wishID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String wishName = rs.getString("wishName");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String link = rs.getString("link");
                wish = new Wish(wishID, wishName, price, description, link);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wish;
    }

    //Find wishlist by ID
    @Override
    public Wishlist findWishlistByID(int wishlistID) {
        Wishlist wishlist = null;
        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "SELECT * FROM wishlist WHERE wishlist_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, wishlistID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int userID = rs.getInt("users_id");
                String listName = rs.getString("wishlistName");
                wishlist = new Wishlist(userID, listName, wishlistID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wishlist;
    }

    //DONE
    //____________________________________________________________________________________________________________________________________________\\
    //NOT DONE


    //Delete User
    public void deleteUser(int userID) {

        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL1 = "DELETE FROM users WHERE users_id = ?";
            String SQL2 = "DELETE FROM wishlist WHERE wishlist_id = ?";
            String SQL3 = "DELETE FROM wishes WHERE wishlist_id = ?";
            conn.setAutoCommit(false);

            PreparedStatement pstmt4 = conn.prepareStatement(SQL3);
            pstmt4.setInt(1, userID);
            pstmt4.executeUpdate();

            PreparedStatement pstmt3 = conn.prepareStatement(SQL2);
            pstmt3.setInt(1, userID);
            pstmt3.executeUpdate();

            PreparedStatement pstmt2 = conn.prepareStatement(SQL1);
            pstmt2.setInt(1, userID);
            int userDeleted =  pstmt2.executeUpdate();

            if (userDeleted > 0) {
                conn.commit();
                System.out.println("User with ID " + userID + " has been deleted");
            } else {
                conn.rollback();
                System.out.println("No user with ID " + userID + " found to delete.");
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    //Edit Wish
    @Override
    public void editWish(Wish wish) {
    }

}


