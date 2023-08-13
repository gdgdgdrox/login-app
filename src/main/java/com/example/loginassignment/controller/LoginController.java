package com.example.loginassignment.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.loginassignment.model.CustomUserDetails;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    
    @GetMapping("/")
    public String homePage(Authentication auth, Model model){
        if (auth != null){
            CustomUserDetails cud = (CustomUserDetails)auth.getPrincipal();
            model.addAttribute("userDetails", cud);
        }
        return "home-translated";
    }

    @GetMapping("/restricted")
    public String restrictedPage(){
        return "restricted";
    }

}
