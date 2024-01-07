package com.umiuni.shop.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Refers to 'login.html' in 'src/main/resources/templates'
    }

    @GetMapping("/success")
    public String successPage() {
        return "success"; // Refers to 'login.html' in 'src/main/resources/templates'
    }

}
