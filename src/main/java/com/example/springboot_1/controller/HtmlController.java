package com.example.springboot_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class HtmlController {

    @GetMapping("/login-html")
    public String login(){ return "login";}

    @GetMapping("/index-html")
    public String index(){ return "index";}

    @GetMapping("/register-html")
    public String register(){ return "register";}
}

