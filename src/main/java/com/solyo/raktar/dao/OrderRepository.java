package com.solyo.raktar.dao;

import com.solyo.raktar.model.Category;
import com.solyo.raktar.model.Order;
import com.solyo.raktar.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o from Order o")
    List<Order> findAll();

    @Query("select o from Order o where o.status = 'Created'")
    List<Order> findAvailable();

    @Query("select o from Order o where o.transporterName = :name")
    List<Order> findByUserName(String name);
}
