package com.vyatsu.task14.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @GetMapping("/index")
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String signIn() {
        return "redirect:/products";
    }

    @GetMapping("/logout")
    public String logout() {
        return "index";
    }
}
