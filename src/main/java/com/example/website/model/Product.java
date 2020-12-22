package com.example.website.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;

@Document(collection="product")
public class Product {

    @Id
    private long id;

    private String name;
    private double price;
    private boolean available;
    private HashSet<String> category;

    public Product(long id, String name, double price, boolean available, HashSet<String> category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public HashSet<String> getCategory() {
        return category;
    }

    public void setCategory(HashSet<String> category) {
        this.category = category;
    }
}
