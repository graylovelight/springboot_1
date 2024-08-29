package com.example.springboot_1.service;



import com.example.springboot_1.dao.menuRepository;
import com.example.springboot_1.entities.menu_items;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Service
public class menu_server {
    menuRepository menuRepository;

    public menu_server(menuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<menu_items> find(){
        return menuRepository.findAll();
    }

    public menu_items findByid(@RequestParam int id){
        return menuRepository.findByid(id);
    }

    public boolean insert(@RequestParam int id,
                          @RequestParam String name,
                          @RequestParam int parent_id,
                          @RequestParam int role,
                          @RequestParam Date created_date,
                          @RequestParam Date last_modified_date){
        menu_items menu_items = new menu_items(id, name, parent_id,role,created_date,last_modified_date);
        menuRepository.save(menu_items);
        return true;
    }

    public boolean delete(@PathVariable int id){
        menuRepository.deleteByid(id);
        return true;
    }

}
