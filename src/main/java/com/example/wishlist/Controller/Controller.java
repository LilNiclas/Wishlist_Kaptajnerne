package com.example.wishlist.Controller;

import com.example.wishlist.DTO.WishDTO;
import com.example.wishlist.DTO.WishlistDTO;
import com.example.wishlist.Model.Wish;
import com.example.wishlist.Model.Wishlist;
import com.example.wishlist.Service.Service;
import org.apache.catalina.valves.JsonErrorReportValve;
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

    //View Wishlists
    @GetMapping(path = "home")            //localhost:8083/wishu/home
    public String showWishlists(Model model) {
        List<Wishlist> wishlists = service.getWishlists();
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
    @GetMapping(path = "home/addwishlist")
    public String showCreateWishlist(Model model){
        WishlistDTO wishlist = new WishlistDTO();
        model.addAttribute("wishlist", wishlist);
        return "createWishlist";
    }

    @PostMapping(path = "home/addwishlist")
    public String addWishlist(@ModelAttribute("wishlist") WishlistDTO wishlist){
        service.addWishlist(wishlist);
        return "redirect:/wishu/home";
    }


  //Add Wish
    @GetMapping(path = "home/wishlists/{wishlistID}/addwish")
    public String showCreateWish(@PathVariable("wishlistID") int wishlistID, Model model) {
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


}