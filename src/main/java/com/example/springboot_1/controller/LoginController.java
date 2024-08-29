package com.example.springboot_1.controller;

import com.example.springboot_1.jwt.ResponseResult;
import com.example.springboot_1.service.Login_server;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class LoginController {
    @Resource
    private Login_server loginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestParam String username, @RequestParam String password) {
        return loginService.login(username, password);
    }

}