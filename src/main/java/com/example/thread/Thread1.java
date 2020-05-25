package com.example.thread;


import java.util.concurrent.CountDownLatch;

public class Thread1 {

    private int num = 0;

    public int getNum() {
        return num;
    }

    public synchronized void addNum() {
        this.num = num + 1;
    }


    public static void main(String[] args) throws InterruptedException {
        Thread1 t1 = new Thread1();

        CountDownLatch countDownLatch = new CountDownLatch(10000);

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                t1.addNum();
                System.out.println(t1.getNum());
            }, "A" + i).start();
            countDownLatch.countDown();
        }
        countDownLatch.await();
        System.out.println("=======  end =========");
        System.out.println(t1.getNum());
    }



}
