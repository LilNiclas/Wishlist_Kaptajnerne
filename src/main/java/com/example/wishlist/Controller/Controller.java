package com.example.wishlist.Controller;

import com.example.wishlist.Model.Wish;
import com.example.wishlist.Model.Wishlist;
import com.example.wishlist.Service.Service;
import org.apache.catalina.valves.JsonErrorReportValve;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

}