package com.example.thread.threadlocal.scene2;

public class Service1 {


    public static void service1() {
        User user = new User("xuyy");
        UserContextHolder.holder.set(user);
        System.out.println("service1 set username = "+ user.getName());
        Service2.service2();
    }
}
