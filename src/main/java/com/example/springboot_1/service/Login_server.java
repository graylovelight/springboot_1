package com.example.springboot_1.service;

import com.example.springboot_1.jwt.ResponseResult;
import com.example.springboot_1.entities.User;
import com.example.springboot_1.jwt.JWT_tool;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class Login_server {
    @Resource
    private User_server userService;

    @Resource
    private JWT_tool jwtTool;

    @Resource
    private AuthenticationManager authenticationManager;

    public ResponseResult login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (Objects.isNull(authentication)) {
            return ResponseResult.fail("用户不存在");
        }

        User user = userService.findUserByUsername(username);
        Long userid = (long) user.getId();

        String token = jwtTool.Jwtbuild(Long.toString(userid));
        Map<String, String> map = Map.of("token", token);
        return ResponseResult.success(map);
    }

}
