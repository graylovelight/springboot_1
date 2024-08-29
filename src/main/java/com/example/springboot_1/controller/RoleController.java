package com.example.springboot_1.controller;

import com.example.springboot_1.entities.Role;
import com.example.springboot_1.service.Role_server;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/Role")
public class RoleController {

    @Resource
    private Role_server role_server;

    @GetMapping
    public List<Role> findAll(){
        return role_server.find();
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestParam int id,
                          @RequestParam String name){
        if (role_server.existsByid(id)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("id重复");
        }
        if (role_server.existsByname(name)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("角色名重复");
        }
        role_server.createRole(id,name);
        return ResponseEntity.ok("200");
    }

    @GetMapping("/findbyid")
    public Role findbyid(@RequestParam int id) {
        return role_server.findByid(id);
    }

    @GetMapping("/deletebyid")
    public boolean delete(@RequestParam int id){
        role_server.delete(id);
        return true;
    }

}
