package com.solyo.raktar.web;

import com.solyo.raktar.dao.OrderRepository;
import com.solyo.raktar.dao.ProductRepository;
import com.solyo.raktar.model.Order;
import com.solyo.raktar.model.User;
import com.solyo.raktar.security.CustomUserDetails;
import com.solyo.raktar.web.DTO.CreateOrder;
import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.extras.springsecurity5.util.SpringSecurityContextUtils;

import javax.websocket.server.PathParam;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class OrderController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/createorder")
    public String CreateOrderScreen(Model model){
        var products = this.productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("order", new CreateOrder());
        return "orders/createorder";
    }

    @PostMapping("/order/create")
    public String CreateOrder(CreateOrder order){
        var newOrder = new Order();
        var product = productRepository.findById(order.getProductId());
        if(product.isEmpty())
            return "redirect:/";
        var validProduct = product.get();
        newOrder.setCreationTime(GetCurrentTime());
        newOrder.setQuantity(order.getQuantity());
        newOrder.setTotalPrice(order.getQuantity() * validProduct.getPrice());
        newOrder.setStatus("Created");
        newOrder.setTransporterName(null);
        newOrder.setProduct(validProduct);
        this.orderRepository.save(newOrder);
        return "redirect:/";
    }

    @GetMapping("/vieworder")
    public String ViewOrders(Model model){
        var orders = this.orderRepository.findAll();
        System.out.println("Orders" + orders.toString());
        model.addAttribute("orders", orders);
        return "orders/listorder";
    }

    @GetMapping("/order/available")
    public String AvailableOrders(Model model){
        var orders = this.orderRepository.findAvailable();
        System.out.println("Orders" + orders.toString());
        model.addAttribute("orders", orders);
        return "orders/listorder";
    }

    @PostMapping("/order/delete/{id}")
    public String RemoveOrder(@PathVariable long id){
        this.orderRepository.deleteById(id);
        return "redirect:/vieworder";
    }

    @PostMapping("/order/take/{id}")
    public String AcceptOrder(@PathVariable long id){
        var order = this.orderRepository.findById(id);
        if(order.isEmpty())
            return "/order/available";
        var validOrder = order.get();
        validOrder.setStatus("Taken");
        var a = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var currentUser = (CustomUserDetails)a;
        System.out.println(currentUser);
        validOrder.setTransporterName(currentUser.getUsername());
        this.orderRepository.save(validOrder);
        return "redirect:/order/available";
    }

    @PostMapping("/order/abort/{id}")
    public String AbortOrder(@PathVariable long id){
        var order = this.orderRepository.findById(id);
        if(order.isEmpty())
            return "/order/available";
        var validOrder = order.get();
        validOrder.setStatus("Created");
        validOrder.setTransporterName(null);
        this.orderRepository.save(validOrder);
        return "redirect:/order/my";
    }

    @PostMapping("/order/complete/{id}")
    public String CompleteOrder(@PathVariable long id){
        var order = this.orderRepository.findById(id);
        if(order.isEmpty())
            return "/order/available";
        var validOrder = order.get();
        validOrder.setStatus("Completed");
        this.orderRepository.save(validOrder);
        return "redirect:/order/my";
    }

    @GetMapping("/order/my")
    public String myOrders(Model model){
        var context = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var currentUser = (CustomUserDetails)context;
        var orders = this.orderRepository.findByUserName(currentUser.getUsername());
        model.addAttribute("orders", orders);
        return "orders/listorder";
    }


    private String GetCurrentTime(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
