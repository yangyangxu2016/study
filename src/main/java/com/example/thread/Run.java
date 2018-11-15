package com.example.thread;

public class Run {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        ThreadA threadA = new ThreadA(lock);
        threadA.start();
        Thread.sleep(50);

        ThreadB threadB = new ThreadB(lock);
        threadB.start();
    }
}
