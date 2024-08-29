package com.example.springboot_1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import com.example.springboot_1.entities.Business;


@Repository("BusinessRepository")
public interface BusinessRepository extends JpaRepository<Business, Long> {

    Business findByBusinessid(int id);
    @Modifying(clearAutomatically = true)
    @Transactional

    void deleteByBusinessid(int id);

}