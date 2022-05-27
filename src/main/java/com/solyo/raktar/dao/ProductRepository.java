package com.solyo.raktar.dao;

import com.solyo.raktar.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{

    @Query("SELECT p from Product p where p.isDeleted = false")
    List<Product> findAll();

    @Query("select p from Product  p where p.category.name = :name and p.isDeleted = false")
    List<Product> findByCategoryName(String name);
}