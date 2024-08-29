package com.example.springboot_1.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;
import com.example.springboot_1.entities.menu_items;
import com.example.springboot_1.service.menu_server;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private menu_server menu_server;
    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<menu_items> findAll() {
        return menu_server.find();
    }

    @GetMapping("/findbyid")
    public menu_items findbyid(@RequestParam int id) {
        return menu_server.findByid(id);
    }

    @GetMapping("/deletebyid")
    public boolean delete(@RequestParam int id) {
        return menu_server.delete(id);
    }

    @GetMapping("/select")
    public List<Map<String, Object>> select() {
        return jdbcTemplate.queryForList("SELECT * FROM menu_items");
    }

}

