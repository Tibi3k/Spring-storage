package com.solyo.raktar.web;

import com.solyo.raktar.dao.CategoryRepository;
import com.solyo.raktar.dao.ProductRepository;
import com.solyo.raktar.model.Product;
import com.solyo.raktar.web.DTO.CreateProduct;
import com.solyo.raktar.web.DTO.UpdateProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @GetMapping("/")
    public String ListAllProducts(Model model){
        var products= this.productRepository.findAll();
        var categories = this.categoryRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "listproduct";
    }

    @RequestMapping("/createproduct")
    public String createProduct(Model model){
        var list = this.categoryRepository.findAll();
        model.addAttribute("product", new Product());
        model.addAttribute("categories", list);
        return "products/addproduct";
    }

    @PostMapping("/")
    public String createProduct(@ModelAttribute CreateProduct product, Model model){
        var newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setQuantity(product.getQuantity());
        newProduct.setDeleted(false);
        var category = this.categoryRepository.findCategoryByName(product.getCategory());
        newProduct.setCategory(category);
        this.productRepository.save(newProduct);
        return "redirect:/";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable long id, Model model){
        var product = this.productRepository.findById(id);
        var categories = this.categoryRepository.findAll();
        if(product.isEmpty())
            return "redirect:/";
        var updateProduct  = new UpdateProduct();
        updateProduct.setCategory(product.get().getCategory().getName());
        updateProduct.setName(product.get().getName());
        updateProduct.setDescription(product.get().getDescription());
        updateProduct.setPrice(product.get().getPrice());
        updateProduct.setQuantity(product.get().getQuantity());
        updateProduct.setId(product.get().getId());
        model.addAttribute("product", updateProduct);
        model.addAttribute("categories", categories);
        System.out.println(product);
        return "products/updateproduct";
    }

    @PostMapping("/product/update")
    public String updateProduct(@ModelAttribute UpdateProduct product, Model model){
        System.out.println("prod cat" + product.getCategory());
        var dbProd = this.productRepository.findById(product.getId());
        if(dbProd.isEmpty()){
            return "redirect:/";
        }
        var existingDbProd = dbProd.get();
        existingDbProd.setDeleted(true);
        this.productRepository.save(existingDbProd);
        var newDbProd = new Product();
        newDbProd.setDescription(product.getDescription());
        newDbProd.setName(product.getName());
        newDbProd.setPrice(product.getPrice());
        newDbProd.setQuantity(product.getQuantity());
        var category = this.categoryRepository.findCategoryByName(product.getCategory());
        newDbProd.setCategory(category);
        newDbProd.setDeleted(false);
        this.productRepository.save(newDbProd);
        return "redirect:/";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        var existingProd = this.productRepository.findById(id);
        if(existingProd.isEmpty())
            return "redirect:/";
        var oldProd = existingProd.get();
        oldProd.setDeleted(true);
        this.productRepository.save(oldProd);
        return "redirect:/";
    }
}
