package com.example.springboot_1.jwt;

import com.example.springboot_1.entities.User;
import com.example.springboot_1.service.Security_server;
import com.example.springboot_1.service.User_server;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Resource
    private JWT_tool jwtTool;

    @Resource
    private User_server userService;

    @Resource
    private Security_server loginUserDetailService;

    private void handleAccessDenied(HttpServletResponse response) throws IOException {
        ResponseResult accessDeniedResult = ResponseResult.result(403, "权限不足，拒绝访问");
        String value = new ObjectMapper().writeValueAsString(accessDeniedResult);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(value);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Optional<String> token = Optional.ofNullable(request.getHeader("Authorization"));

        token.ifPresent(authtoken -> {
            try {
                // 验证并解析token
                Claims claims = jwtTool.Jwtanalysis(authtoken);
                String userid = claims.getSubject();

                // 获取用户信息
                User user = userService.findUserById(Long.parseLong(userid));
                UserDetails nowuser = loginUserDetailService.loadUserByUsername(user.getUsername());

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, nowuser.getAuthorities());
                // 将用户信息存入SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (Exception e) {
                e.printStackTrace();
                ResponseResult responseResult = ResponseResult.result(HttpServletResponse.SC_UNAUTHORIZED, "token错误", e.getMessage());
                try {
                    String value = new ObjectMapper().writeValueAsString(responseResult);
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().write(value);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        filterChain.doFilter(request, response);
    }
}
