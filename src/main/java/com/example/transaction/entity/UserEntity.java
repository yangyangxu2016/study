package com.example.transaction.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * @author xuyy
 */
@Entity(name = "customer")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public UserEntity() {
    }

    public UserEntity(String name) {
        this.name = name;
    }

}
