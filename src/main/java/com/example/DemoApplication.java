package com.example;

import com.example.filter.CustomFilter;
import com.xuyy.user.EnableUserClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@EnableUserClient
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<CustomFilter> registration() {
        CustomFilter filter = new CustomFilter();
        FilterRegistrationBean registration = new FilterRegistrationBean<CustomFilter>(filter);
        registration.addUrlPatterns("/*");
        return registration;
    }

}
