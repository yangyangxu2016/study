package com.example.thread.threadlocal.scene2;

public class Service3 {


    public static void service3() {
        User user = UserContextHolder.holder.get();
        System.out.println("service2 get username = " + user.getName());
    }
}

