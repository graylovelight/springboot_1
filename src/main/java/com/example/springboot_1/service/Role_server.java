package com.example.springboot_1.service;

import com.example.springboot_1.dao.RoleRepository;
import com.example.springboot_1.entities.Role;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class Role_server {

    RoleRepository roleRepository;

    public Role_server(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> find(){
        return roleRepository.findAll();
    }

    public void createRole(@RequestParam int id,
                           @RequestParam String name) {
        Role role = new Role(id, name);
        roleRepository.save(role);
    }

    public Role findByid(@RequestParam int id){
        return roleRepository.findByid(id);
    }

    public void delete(@PathVariable int id){
        roleRepository.deleteByid(id);
    }

    public boolean existsByid(int id) {
        return roleRepository.existsByid(id);
    }

    public boolean existsByname(String name) {
        return roleRepository.existsByname(name);
    }
}

