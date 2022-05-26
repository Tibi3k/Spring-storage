package com.solyo.raktar.web;

import com.solyo.raktar.dao.CategoryRepository;
import com.solyo.raktar.model.Category;
import com.solyo.raktar.web.DTO.CreateCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Secured("user")
    @GetMapping("/createcategory")
    public String CreateCategory(Model model) {
        var categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("category", new CreateCategory());
        return "createcategory";
    }

    @Secured("user")
    @PostMapping("/category")
    public String CategoryCreated(@ModelAttribute CreateCategory category) {
        var newCategory = new Category();
        newCategory.setName(category.getName());
        if(category.getParentCategory().equals("null")){
            newCategory.setParentCategory(null);
        } else {
            var parentCategory = categoryRepository.findCategoryByName(category.getParentCategory());
            newCategory.setParentCategory(parentCategory);
        }
        this.categoryRepository.save(newCategory);
        return "redirect:/";
    }
}
