package com.solyo.raktar.dao;

import com.solyo.raktar.model.Category;
import com.solyo.raktar.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT p from Category p")
    List<Category> findAll();

    @Query("Select c from Category c where c.name = :name")
    Category findCategoryByName(String name);
}
