package com.solyo.raktar.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy="category")
    private List<Product> products;

    @OneToMany(mappedBy="parentCategory")
    private List<Category> childCategories;

    @ManyToOne
    @JoinColumn(name="parentCategoryId")
    private Category parentCategory;


    public Category() { }

    public Category(long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
