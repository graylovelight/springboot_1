package com.example.springboot_1.service;

import com.example.springboot_1.entities.Role;
import com.example.springboot_1.entities.Role_Permission;
import com.example.springboot_1.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class Security_server implements UserDetailsService {
    @Resource
    private User_server userService;
    @Resource
    private Role_server roleService;
    @Resource
    private Role_Permission_server role_permission_Service;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        Role role = roleService.findByid(user.getRole_id());
        if (role != null){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            List<Role_Permission> rolePermissions = role_permission_Service.findByroleid(user.getRole_id());
            for (Role_Permission rolePermission : rolePermissions) {
                authorities.add(new SimpleGrantedAuthority(rolePermission.getPermissionName()));
            }
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
