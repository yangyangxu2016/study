package com.example.springboot.transaction;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping(value = "api/customer")
public class CustomerController {


    @Autowired
    CustomerServiceAnnotation customerServiceAnnotation;

    @Autowired
    CustomerServiceCode customerServiceCode;

    @Autowired
    CustomerRepository customerRepository;


    @RequestMapping(value = "/annotation")
    public Customer createInAnnotation(@RequestBody Customer customer){
        log.info("create customer in annotation,customer:{} ",customer);
        return customerServiceAnnotation.create(customer);

    }


    @RequestMapping(value = "/code")
    public Customer createInCode(@RequestBody Customer customer){
        log.info("create customer in code,customer:{} ",customer);
        return customerServiceCode.create(customer);
    }

    @RequestMapping(value ="" )
    public List<Customer> getAll() {
        return  customerRepository.findAll();
    }



}
