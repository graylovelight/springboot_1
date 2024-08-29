package com.example.springboot_1.dao;

import com.example.springboot_1.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("PermissionRepository")
public interface PermissionRepository extends JpaRepository<Permission,Long> {
    Permission findByid(int id);
    @Modifying(clearAutomatically = true)
    @Transactional

    void deleteByid(int id);
}
