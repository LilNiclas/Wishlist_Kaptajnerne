package com.example.wishlist.Controller;

import com.example.wishlist.DTO.WishlistDTO;
import com.example.wishlist.Service.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping(path = "kea")
public class Controller {

    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    //View wishlists
    @GetMapping(path = "/")      //localhost:8082/kea/
    public String getWishlists (Model model) {
        List<WishlistDTO> lists = service.getWishlists();
        model.addAttribute("name", lists);
        return "index";
    }



}
