package com.solyo.raktar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String description;

    private int price;
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CategoryID")
    private Category category;

    private boolean isDeleted;

    @JsonIgnore
    @OneToMany(mappedBy="product")
    private List<Order> orders;

    public Product(){}

    public Product(long id, Category category, int price, int quantity, String description, String name){
        this.id = id;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.name = name;

    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category.getName() + '\'' +
                '}';
    }
}
