package com.example.springboot_1.service;



import com.example.springboot_1.dao.BusinessRepository;
import com.example.springboot_1.entities.Business;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class Business_server {
    BusinessRepository businessRepository;


    public Business_server(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    public List<Business> find(){
        return businessRepository.findAll();
    }

    public Business findByid(@RequestParam int id){
        return businessRepository.findByBusinessid(id);
    }

    public boolean insert(
                          @RequestParam String password,
                          @RequestParam String businessname,
                          @RequestParam String businessaddress,
                          @RequestParam String businessexpain,
                          @RequestParam int starPrice,
                          @RequestParam int deliveryPrice,
                          @RequestParam String phone){
        Business new_business  = new Business();
        new_business.setPassword(password);
        new_business.setBusinessname(businessname);
        new_business.setBusinessaddress(businessaddress);
        new_business.setBusinessexpain(businessexpain);
        new_business.setStarprice(starPrice);
        new_business.setDeliveryprice(deliveryPrice);
        new_business.setPhone(phone);
        businessRepository.save(new_business);
        return true;
    }

    public boolean delete(@PathVariable int id){
        businessRepository.deleteByBusinessid(id);
        return true;
    }

}
