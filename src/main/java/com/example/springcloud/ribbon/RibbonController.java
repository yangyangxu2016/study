package com.example.springcloud.ribbon;

import com.example.serialize.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {


    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/get")
    public String get() {
//        ResponseEntity<String> json = restTemplate.getForEntity("http://localhost:8080/user/get?id=1", String.class);
        ResponseEntity<String> json = restTemplate.getForEntity("https://user-service/", String.class);
        String body = json.getBody();
        System.out.println(body);
        return body;
    }
}
