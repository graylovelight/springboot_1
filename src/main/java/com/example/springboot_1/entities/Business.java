package com.example.springboot_1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "business")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int businessid;
    private String password;
    private String businessname;
    private String businessaddress;
    private String businessexpain;
    private int starprice;
    private int deliveryprice;
    private String phone;
    //事实证明，大写的表名会带来一些不必要的错误，全用小写比较好

}
