package com.example.springboot_1.dao;

import com.example.springboot_1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsername(String username);

    User findUserByUsername(String username);
    @Modifying(clearAutomatically = true)
    @Transactional

    void deleteByUsername(String username);

    User findUserById(int id);
}
