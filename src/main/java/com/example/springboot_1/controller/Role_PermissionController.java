package com.example.springboot_1.controller;

import com.example.springboot_1.entities.Role_Permission;
import com.example.springboot_1.service.Role_Permission_server;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Role_Permission")
public class Role_PermissionController {

    @Resource
    private Role_Permission_server role_permission_server;

    @GetMapping
    public List<Role_Permission> findAll(){
        return role_permission_server.find();
    }

    @PostMapping("/insert")
    public boolean insert(@RequestParam int role_id,
                          @RequestParam int permission_id){
        role_permission_server.createRole_Permission(role_id,permission_id);
        return true;
    }

    @GetMapping("/findByroleid")
    public List<Role_Permission> findByroleid(@RequestParam int id) {
        return role_permission_server.findByroleid(id);
    }

    @GetMapping("/deletebyroleid")
    public boolean deletebyroleid(@RequestParam int id){
        role_permission_server.deletebyroleid(id);
        return true;
    }

    @GetMapping("/deleteBypermissionid")
    public boolean deleteBypermissionid(@RequestParam int id){
        role_permission_server.deleteBypermissionid(id);
        return true;
    }


}
