package com.example.springboot.transaction;

import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepository extends JpaRepository<Customer,Long> {



}
