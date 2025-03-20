package com.buccodev.contact_book.infrastructure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/api/contact-book")
    public String home() {
        return "redirect:/index.html";
    }
}
