package com.example.thread;


public class Test2 {

    private int num = 0;

    public void addNum() {
        int i = 0;
        while (i++ < 10000) {
            num += 1;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Test2 t = new Test2();

        Thread t1 = new Thread(() -> {
            t.addNum();
        });

        Thread t2 = new Thread(() -> {
            t.addNum();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(t.num);
    }


}
