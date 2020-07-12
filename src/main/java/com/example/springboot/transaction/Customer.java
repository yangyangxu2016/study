package com.example.springboot.transaction;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity(name = "customer")
@Slf4j
@Data
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_name",unique = true)
    private String username;

    private String password;

    private String role;
}
