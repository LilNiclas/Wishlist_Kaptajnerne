package com.example.wishlist.Controller;

import com.example.wishlist.DTO.WishlistDTO;
import com.example.wishlist.Service.Service;
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

    //View wishlists
    @GetMapping(path = "home")            //localhost:8083/wishu/home
    public String showWishlists(Model model) {
        List<WishlistDTO> lists = service.getWishlists();
        model.addAttribute("name", lists);
        return "index";
    }

    @GetMapping(path = "wishes/{listName}")  //localhost:8083/wishu/wishes/{listName}
    public String showWishses (@PathVariable String listName, Model model) {
        return "wishes";
    }

}