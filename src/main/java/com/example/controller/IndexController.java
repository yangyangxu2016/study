package com.example.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuyy
 */
@RestController()
public class IndexController {

    @GetMapping(value = "/index")
    public String index() {
        return "hello，index";
    }


}
