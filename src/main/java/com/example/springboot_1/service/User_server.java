package com.example.springboot_1.service;

import com.example.springboot_1.dao.UserRepository;
import com.example.springboot_1.entities.User;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class User_server {

    @Resource
    public UserRepository userRepository;

    public List<User> find(){
        return userRepository.findAll();
    }
    public void addUser(String username, String password) {
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        User user_new = new User();
        user_new.setUsername(username);
        user_new.setPassword(encodedPassword);
        user_new.setRole_id(1);
        userRepository.save(user_new);
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
    public User findUserById(long id) {
        return userRepository.findUserById((int) id);
    }


    public void delete(@PathVariable String username){
        userRepository.deleteByUsername(username);
    }
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    public boolean updateRoleByUsername(String username, int roleId) {
        User user = findUserByUsername(username);
        if (user != null) {
            user.setRole_id(roleId);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}

