package com.example.springboot_1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import com.example.springboot_1.entities.menu_items;

@Repository("menuRepository")
public interface menuRepository extends JpaRepository<menu_items, Long> {

    menu_items findByid(int id);
    @Modifying(clearAutomatically = true)
    @Transactional

    void deleteByid(int id);

}