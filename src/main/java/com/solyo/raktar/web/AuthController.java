package com.solyo.raktar.web;

import com.solyo.raktar.dao.UserRepository;
import com.solyo.raktar.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String ShowRegisterForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String RegisterUser(User user, Model model){
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String ShowLoginForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }
}
