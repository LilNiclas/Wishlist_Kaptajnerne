package com.example.wishlist.Controller;

import com.example.wishlist.DTO.WishDTO;
import com.example.wishlist.DTO.WishlistDTO;
import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Model.Wishlist;
import com.example.wishlist.Repository.IRepository;
import com.example.wishlist.Service.Service;
import org.apache.catalina.core.ApplicationContext;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.valves.JsonErrorReportValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping(path = "wishu")
public class Controller {

    IRepository repository;
    private Service service;
    public Controller(Service service, @Qualifier("Repository") IRepository iRepository) {
        this.service = service;
        this.repository=iRepository;
    }

    //View Users
    @GetMapping (path = "")          //localhost:8083/wishu
    public String showUsers (Model model) {
        List<User> users = service.getUsers();
        model.addAttribute("user", users);
        return "users";
    }

    //View Wishlists
    @GetMapping(path = "home/{email}")            //localhost:8083/wishu/home/{email}
    public String showWishlists(@PathVariable String email, Model model) {
        List<Wishlist> wishlists = service.getWishlists(email);
        model.addAttribute("wishlists", wishlists);
        return "index";
    }

    //View Wishes
    @GetMapping(path = "wishes/{wishlistID}")  //localhost:8083/wishu/wishes/{listID}
    public String showWishses (@PathVariable int wishlistID, Model model) {
        List<Wish> wishes = service.getWishes(wishlistID);
        model.addAttribute("wishes", wishes);
        return "wishes";
    }


    //Add Wishlist
    @GetMapping(path = "home/addwishlist")      //localhost:8083/wishu/home/addwishlist
    public String showCreateWishlist(Model model){
        WishlistDTO wishlist = new WishlistDTO();
        model.addAttribute("wishlist", wishlist);
        return "createWishlist";
    }

    @PostMapping(path = "home/addwishlist")      //localhost:8083/wishu/home/addwishlist
    public String addWishlist(@ModelAttribute("wishlist") WishlistDTO wishlist){
        service.addWishlist(wishlist);
        return "redirect:/wishu/home";
    }


  //Add Wish
    @GetMapping(path = "home/wishlists/{wishlistID}/addwish")    //localhost:8083/wishu/home/addwishlist
    public String showCreateWish(@PathVariable("wishlistID") int wishlistID, Model model) {
        WishDTO wish = new WishDTO();
        model.addAttribute("wish", wish);
        model.addAttribute("wishlistIDs", wishlistID);
        return "createWish";
    }

    @PostMapping(path = "home/wishlists/{wishlistID}/addwish")     //localhost:8083/wishu/home/addwishlist
    public String addWish(@ModelAttribute("wish") WishDTO wishDTO, @PathVariable("wishlistID") int wishlistID) {
        service.addWish(wishDTO, wishlistID);
        return "redirect:/wishu/wishes/" + wishlistID;
    }

    //Delete Wishlist
    @GetMapping("home/delete/{wishlistID}")
    public String showDeleteWishlist(@PathVariable("wishlistID") int wishlistID, Model model){
        model.addAttribute("wishlist", service.findWishlistByID(wishlistID));
        return "deleteWishlist";
    }

    @PostMapping("home/delete/{wishlistID}")
    public String deleteWishlist(@ModelAttribute("wishlistID") int wishlistID){
        service.deleteWishlist(wishlistID);
        return "redirect:/wishu/home";
    }

    @GetMapping(value = {"/editWish"})
    public String showEditWish(HttpServletRequest request, @PathVariable("id") int id, Model model) {
        if (request.getSession().getAttribute("userId") == null) {
            return "login";
        }
        int user1 = (int) request.getSession().getAttribute("userId");
        model.addAttribute("id", id);
        model.addAttribute("wish", repository.getWishes(id));
        return "editWish";
    }

    @PostMapping(value = {"/editWish"})
    public String editWish(@ModelAttribute Wish wish) {
        int id = wish.getWishID();
        repository.editwish(wish);
        return "editWish" + id;
    }


}