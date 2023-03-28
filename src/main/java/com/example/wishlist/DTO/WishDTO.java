package com.example.wishlist.DTO;

public class WishDTO {
    private String name;
    private double price;
    private String description;
    private String link;

      public WishDTO(String name, double price, String description, String link) {
            this.name = name;
            this.price = price;
            this.description = description;
            this.link = link;
        }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }






}
