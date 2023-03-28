package com.example.wishlist.Repository;


import com.example.wishlist.DTO.WishlistDTO;
import com.example.wishlist.Util.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository("wishlists")
public class Repository implements IRepository {


    @Value("${spring.datasource.url}")
    private String db_url;

    @Value("${spring.datasource.username}")
    private String uid;

    @Value("${spring.datasource.password}")
    private String pwd;


    //view wishlists
    @Override
    public List<WishlistDTO> getWishlists() {
        List<WishlistDTO> wishlists = new ArrayList<>();

        try {
            Connection conn = ConnectionManager.getConnection(db_url, uid, pwd);
            String SQL = "select name from wishlist;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                String listName = rs.getString("name");

                wishlists.add(new WishlistDTO(listName));
            }
            return wishlists;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
