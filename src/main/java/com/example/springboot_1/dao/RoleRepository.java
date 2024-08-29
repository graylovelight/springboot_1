package com.example.springboot_1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import com.example.springboot_1.entities.Role;

@Repository("RoleRepository")
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByid(int id);
    @Modifying(clearAutomatically = true)
    @Transactional

    void deleteByid(int id);

    boolean existsByid(int id);

    boolean existsByname(String name);
}
