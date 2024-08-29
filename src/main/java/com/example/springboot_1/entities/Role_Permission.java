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
@Table(name = "Role_Permission")
public class Role_Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "role_id")
    private int roleid;
    @Column(name = "permission_id")
    private int permissionid;
    @Column(name="role_name")
    private String roleName;
    @Column(name="permission_name")
    private String permissionName;
}
