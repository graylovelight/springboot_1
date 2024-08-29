package com.example.springboot_1.service;

import com.example.springboot_1.dao.Role_PermissionRepository;
import com.example.springboot_1.entities.Role_Permission;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class Role_Permission_server {
    @Resource
    private Role_PermissionRepository role_permissionRepository;

    public List<Role_Permission> find(){
        return role_permissionRepository.findAll();
    }

    public void createRole_Permission(@RequestParam int role_id,
                           @RequestParam int permission_id) {

        Role_Permission role_Permission_new = new Role_Permission();
        role_Permission_new.setRoleid(role_id);
        role_Permission_new.setPermissionid(permission_id);
        role_permissionRepository.save(role_Permission_new);
    }

    public List<Role_Permission> findByroleid(@RequestParam int id){
        return role_permissionRepository.findByroleid(id);
    }

    public void deletebyroleid(@PathVariable int id){
        role_permissionRepository.deleteByroleid(id);
    }

    public void deleteBypermissionid(@PathVariable int id){
        role_permissionRepository.deleteBypermissionid(id);
    }

}

