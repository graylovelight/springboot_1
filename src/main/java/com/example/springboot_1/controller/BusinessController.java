package com.example.springboot_1.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.springboot_1.entities.Business;
import com.example.springboot_1.service.Business_server;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/business")
public class BusinessController {

    @Resource
    private Business_server business_server;
    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Business> findAll() {
        return business_server.find();
    }

    @GetMapping("/findbyid")
    public Business findbyid(@RequestParam int id) {
        return business_server.findByid(id);
    }

    @GetMapping("/deletebyid")
    public boolean delete(@RequestParam int id) {
        return business_server.delete(id);
    }

    @PostMapping("/insert")
    public boolean insert( @RequestParam String password, @RequestParam String businessname, @RequestParam String businessaddress, @RequestParam String businessexpain, @RequestParam int starPrice, @RequestParam int deliveryPrice, @RequestParam String phone) {
        return business_server.insert(password, businessname, businessaddress, businessexpain, starPrice, deliveryPrice, phone);
    }

    @GetMapping("/select")
    public List<Map<String, Object>> select() {
        return jdbcTemplate.queryForList("SELECT * FROM business");
    }

}

