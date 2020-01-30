package com.iths.webshop.tests;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 2, message = ("Name must be at least 2 character long"))
    private String name;

    private String url;

    private double price;

    @Size(min = 5, message = ("Description must be at least 5 character long"))
    private String description;
    @Pattern(regexp = "^[1-9][0-9]*", message = "Please choose a category")
    @Column(name = "category_id")
    private String categoryId;


    //@OneToMany
    //List <Orderdetail> orderdetail;

    public void setPrice(double price){
        if(price <0) {
            throw new IllegalArgumentException("Price cant be negative");

        }

        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
