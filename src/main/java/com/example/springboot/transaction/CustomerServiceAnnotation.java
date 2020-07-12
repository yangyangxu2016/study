package com.example.springboot.transaction;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
public class CustomerServiceAnnotation {


    @Autowired
    private CustomerRepository customerRepository;


    @Transactional
    public Customer create(Customer customer){
        log.info("CustomerService In Annotation create customer : {}",customer);
        if (customer.getId()!=null)
            throw new RuntimeException("用户已经存在");
        customer.setUsername("Annotation:"+customer.getUsername());
        return customerRepository.save(customer);
    }
}
