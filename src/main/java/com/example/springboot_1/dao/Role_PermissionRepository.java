package com.example.springboot_1.dao;

import com.example.springboot_1.entities.Role_Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("Role_PermissionRepository")
public interface Role_PermissionRepository extends JpaRepository<Role_Permission,Long> {
    List<Role_Permission> findByroleid(int id);
    @Modifying(clearAutomatically = true)
    @Transactional

    void deleteByroleid(int id);
    void deleteBypermissionid(int id);
}
