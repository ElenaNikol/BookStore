package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;


@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Min(value = 0,message = "Brojot na primeroci mora da e pozitiven")
    private Integer numSamples;
    @ManyToOne
    private Category category;
    @Column(name = "image")
    @Lob
    private String image;

    @ManyToMany
    private List<Author> authors;

    private int price;

    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    private List<ShoppingCart> shoppingCarts;


    public Book() {}

    public Book(Long id, String name, Integer numSamples,Category category, String image,Integer price) {
        this.id = id;
        this.name = name;
        this.numSamples = numSamples;
        this.category = category;
        this.image = image;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumSamples() {
        return numSamples;
    }

    public void setNumSamples(Integer numSamples) {
        this.numSamples = numSamples;
    }

    public com.example.demo.model.Category getCategory() {
        return category;
    }

    public void setCategory(com.example.demo.model.Category category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

}
