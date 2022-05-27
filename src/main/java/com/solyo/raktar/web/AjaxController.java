package com.solyo.raktar.web;

import com.solyo.raktar.dao.CategoryRepository;
import com.solyo.raktar.dao.OrderRepository;
import com.solyo.raktar.dao.ProductRepository;
import com.solyo.raktar.model.Order;
import com.solyo.raktar.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AjaxController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @ResponseBody
    @GetMapping("/orders/checknew")
    public List<Order> ListAllProducts(){
        var orders = this.orderRepository.findAvailable();
        return orders;
    }

    @ResponseBody
    @GetMapping("/product/filter")
    public List<Product> ListProductWithQuery(@RequestParam String q){
        var products = this.productRepository.findByCategoryName(q);
        return products;
    }
}
