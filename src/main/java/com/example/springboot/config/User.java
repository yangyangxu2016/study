package com.example.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(value = "spring.object")
public class User{

    private String username;
    private String zpassword;


    public static void main(String[] args) {
        Map<Integer,Integer> map = new HashMap();
    }
}
