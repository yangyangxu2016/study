package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class DemoPublish {

    @Autowired
    private ApplicationContext applicationContext;

    public void publishEvent(DemoEvent demoEvent){
        this.applicationContext.publishEvent(demoEvent);
    }


}
