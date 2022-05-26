package com.solyo.raktar.dao;

import com.solyo.raktar.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{

//    @Query("SELECT p FROM Product p WHERE p.category.name=:categoryName")
//    List<Product> findByCategoryName(@Param("categoryName") String categoryName);

    List<Product> findByPriceGreaterThan(double limit);

    @Query("SELECT p from Product p")
    List<Product> findAll();
}