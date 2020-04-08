package com.example.springboot.controller;


import com.alibaba.fastjson.JSON;
import com.example.springboot.config.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController(value = "/demo")
public class DemoController {

    @Value("${spring.application.name:xuy}")
    public String name;


    @Autowired
    Environment env;

    @Autowired
    User user;

    @GetMapping(value = "/config")
    public String config() {
        Map<String, String> map = new HashMap<>();
        map.put("env-server.port", env.getProperty("server.port"));
        map.put("value-application.name", name);
        map.put("object-username", user.getUsername());
        return JSON.toJSONString(map);
    }


}
