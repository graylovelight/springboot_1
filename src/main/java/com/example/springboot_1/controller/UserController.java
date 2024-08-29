package com.example.springboot_1.controller;

import com.example.springboot_1.entities.User;
import com.example.springboot_1.service.User_server;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
class UserController {

  @Resource
  private User_server userService;

  @GetMapping
  public List<User> findAll(){
    return userService.find();
  }

  @PostMapping("/register")
  public ResponseEntity<String> createUser(@RequestParam("username") String username,
                                           @RequestParam("password") String password) {
    if (userService.existsByUsername(username)) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名重复");
    }
    userService.addUser(username, password);
    return ResponseEntity.ok("200");
  }

  @GetMapping("/findbyusername")
  public User findbyusername(@RequestParam String username) {
    return userService.findUserByUsername(username);
  }

  @GetMapping("/deletebyusername")
  public boolean delete(@RequestParam String username){
    userService.delete(username);
    return true;
  }

  @GetMapping("/updaterole")
  public boolean updateRoleByUsername(@RequestParam String username, @RequestParam int roleId) {
    if (userService.existsByUsername(username)) {
      if (roleId == 4){return false;}
      return userService.updateRoleByUsername(username, roleId);
    } else {
      return false;
    }
  }

}



