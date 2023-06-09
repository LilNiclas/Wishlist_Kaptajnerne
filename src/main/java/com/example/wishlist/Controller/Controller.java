package com.example.wishlist.Controller;

import com.example.wishlist.DTO.UserDTO;
import com.example.wishlist.DTO.WishDTO;
import com.example.wishlist.DTO.WishlistDTO;
import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Model.Wishlist;
import com.example.wishlist.Service.Service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping(path = "wishu")
public class Controller {

    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    private int getUserId(HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute("userId");
        return userId;
    }

    //View Users                               //localhost:8083/wishu
    @GetMapping(path = "")
    public String showUsers(Model model) {
        List<User> users = service.getUsers();
        model.addAttribute("showUser", users);
        model.addAttribute("userID", users);
        return "users";
    }

    //View Wishlists                            //localhost:8083/wishu/home/{username}
    @GetMapping(path = "home/{username}")
    public String showWishlists(@PathVariable String username, Model model) {
        List<Wishlist> wishlists = service.getWishlists(username);
        model.addAttribute("wishlists", wishlists);
        return "wishlist";
    }

    //View Wishes                               //localhost:8083/wishu/wishes/{wishlistID}
    @GetMapping(path = "wishes/{wishlistID}")
    public String showWishses(@PathVariable int wishlistID, Model model) {
        List<Wish> wishes = service.getWishes(wishlistID);
        String username = service.findUsernameByWishlistID(wishlistID);
        model.addAttribute("username", username);
        model.addAttribute("wishes", wishes);
        return "wishes";
    }


    //Add User                                  //localhost:8083/wishu/createUser
    @GetMapping(path = "/createUser")
    public String showCreateUser(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "createUser";
    }

    @PostMapping(path = "/createUser")
    public String addUser(@ModelAttribute("user") UserDTO user) {
        service.addUser(user);
        return "redirect:/wishu";
    }


    //Add Wishlist                              //localhost:8083/wishu/home/addwishlist
    @GetMapping(path = "home/{username}/addwishlist")
    public String showCreateWishlist(Model model, @PathVariable("username") String username) {
        WishlistDTO wishlist = new WishlistDTO();
        model.addAttribute("wishlist", wishlist);
        model.addAttribute("username", username);
        return "createWishlist";
    }

    @PostMapping(path = "home/{username}/addwishlist")
    public String addWishlist(@ModelAttribute("wishlist") WishlistDTO wishlist, @PathVariable("username") String username) {
        service.addWishlist(wishlist, username);
        return "redirect:/wishu/home/" + username;
    }


    //Add Wish                                  //localhost:8083/wishu/home/{wis}hlistID}/addwish
    @GetMapping(path = "home/wishlists/{wishlistID}/addwish")
    public String showCreateWish(Model model, @PathVariable("wishlistID") int wishlistID) {
        WishDTO wish = new WishDTO();
        model.addAttribute("wish", wish);
        model.addAttribute("wishlistIDs", wishlistID);
        return "createWish";
    }

    @PostMapping(path = "home/wishlists/{wishlistID}/addwish")
    public String addWish(@ModelAttribute("wish") WishDTO wishDTO, @PathVariable("wishlistID") int wishlistID) {
        service.addWish(wishDTO, wishlistID);
        return "redirect:/wishu/wishes/" + wishlistID;
    }


    //Delete Wishlist                           //localhost:8083/wishu/home/{email}/delete/{wishlistID}
    @GetMapping(path ="home/delete/{wishlistID}")
    public String showDeleteWishlist(@PathVariable("wishlistID") int wishlistID, Model model) {
        model.addAttribute("wishlist", service.findWishlistByID(wishlistID));
        return "deleteWishlist";
    }

    @PostMapping(path ="home/delete/{wishlistID}")
    public String deleteWishlist(@ModelAttribute("wishlistID") int wishlistID) {
        String username = service.findUsernameByWishlistID(wishlistID);
        service.deleteWishlist(wishlistID);
        return "redirect:/wishu/home/" + username;
    }


    //Edit Wish                                 //localhost:8083/wishu/editWish
    @GetMapping(path = "home/editWish/{id}")
    public String showEditWish(@PathVariable("id") int id, Model model) {
        model.addAttribute("id", id);
        Wish wish = service.getWishes2(id);
        model.addAttribute("wish", wish);
        return "editWish";
    }

    @PostMapping(path = "home/editWish/{id}")
    public String editWish(@ModelAttribute Wish wish, @PathVariable int id) {
        service.editWish(wish, id);

        int wishListId = service.findWishlistId(id);
        return "redirect:/wishu/wishes/" + wishListId;
    }

    //Edit User
    @GetMapping(path = "/editUser/{userID}")
    public String showEditUser(@PathVariable("userID") int userID, Model model) {
        model.addAttribute("userId", userID);
        model.addAttribute("user", service.getUserFromId(userID));
        return "editUser";
    }

    @PostMapping(path = "/editUser/{userID}")
    public String editUser(@ModelAttribute User user, @PathVariable int userID) {
        service.editUser(user);
        return "redirect:/wishu";
    }

    // Delete wish
    @GetMapping(path = "/deleteWish/{wishID}")
    public String showDeleteWish(@PathVariable int wishID, Model model) {
        model.addAttribute("wishID", service.findWishByID(wishID));
        return "deleteWish";
    }

    @PostMapping(path ="/deleteWish/{wishID}")
    public String deleteWish(@ModelAttribute("wishID") int wishID) {
        int wishlistID = service.findWishByID(wishID).getWishID();
        service.deleteWish(wishID);
        return "redirect:/wishu/wishes/" + wishlistID;
    }

    //Delete User
    @GetMapping (path="/deleteUser/{userID}")
    public String showDeleteUser(@PathVariable int userID, Model model) {
        model.addAttribute("userID", userID);
        return "deleteUser";
    }

    @PostMapping (path="/deleteUser/{userID}")
    public String deleteUser(@PathVariable int userID) {
        service.deleteUser(userID);
        return "redirect:/wishu";
    }

}