package com.example.springboot_1.service;

import com.example.springboot_1.dao.PermissionRepository;
import com.example.springboot_1.entities.Permission;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class Permission_server {

    PermissionRepository permissionRepository;

    public Permission_server(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<Permission> find(){
        return permissionRepository.findAll();
    }

    public void createPermission(@RequestParam int id,
                           @RequestParam String name) {
        Permission permission = new Permission(id, name);
        permissionRepository.save(permission);
    }

    public Permission findByid(@RequestParam int id){
        return permissionRepository.findByid(id);
    }

    public void delete(@PathVariable int id){
        permissionRepository.deleteByid(id);
    }
}

