package com.example.thread.threadlocal.scene2;

public class Service2 {


    public static void service2() {

        User user = UserContextHolder.holder.get();
        System.out.println("service2 get username = " + user.getName());
        Service3.service3();
    }
}

