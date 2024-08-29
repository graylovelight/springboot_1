package com.example.springboot_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@RestController
@SpringBootApplication
@EnableJpaRepositories("com.example.springboot_1.dao")
@EntityScan("com.example.springboot_1.entities;")

public class Springboot1Application {
    public static void main(String[] args) {
        SpringApplication.run(Springboot1Application.class, args);
    }

}
