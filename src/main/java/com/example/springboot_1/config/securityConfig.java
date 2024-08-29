package com.example.springboot_1.config;

import com.example.springboot_1.jwt.JwtTokenFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class securityConfig {
    @Resource
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        var permitAllToken = new String[]{"/login-html" , "/user/register" , "/register-html" , "/post/login"};
        var basicToken = new String[]{"/business/findbyid" ,"/business/insert","/menu/findbyid" ,"/menu/insert","/Role","/Permission"};
        var mediumToken = new String[]{"/menu/deletebyid" , "/business/deletebyid","/Role_Permission" , "/Role/findbyid", "/Permission/findbyid"};
        var highToken = new String[]{"/user" , "/user/findbyusername","/Role/insert","/Permission/insert","/Role_Permission/findByroleid","/Role_Permission/insert","/Role_Permission/deletebyroleid"};
        var adminToken = new String[]{"/user/deletebyid" ,"/user/updaterole","/Role/deletebyid","/Permission/deletebyid"};

        http
                .authorizeRequests()
                .requestMatchers(permitAllToken).permitAll()
                .requestMatchers(basicToken).hasAnyRole("basic","medium","high","admin")
                .requestMatchers(mediumToken).hasAnyRole("medium","high","admin")
                .requestMatchers(highToken).hasAnyRole("high","admin")
                .requestMatchers(adminToken).hasAnyRole("admin")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login-html")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index-html")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login-html")
                .permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
}

