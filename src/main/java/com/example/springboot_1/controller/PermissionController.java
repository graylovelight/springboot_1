package com.example.springboot_1.controller;

import com.example.springboot_1.entities.Permission;
import com.example.springboot_1.service.Permission_server;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Permission")
public class PermissionController {

    @Resource
    private Permission_server permission;

    @GetMapping
    public List<Permission> findAll(){
        return permission.find();
    }

    @PostMapping("/insert")
    public boolean insert(@RequestParam int id,
                          @RequestParam String name){
        permission.createPermission(id,name);
        return true;
    }

    @GetMapping("/findbyid")
    public Permission findbyid(@RequestParam int id) {
        return permission.findByid(id);
    }

    @GetMapping("/deletebyid")
    public boolean delete(@RequestParam int id){
        permission.delete(id);
        return true;
    }

}
