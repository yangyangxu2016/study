package com.example.thread;


import java.util.concurrent.CountDownLatch;

public class C {
    public C() {
    }

    private String lock;

    public C(String lock) {
        super();
        this.lock = lock;
    }

    public void getValue() {
        try {

            synchronized (lock) {
                if (ValueObject.value.equals("")) {
                    lock.wait();
                }
                System.out.println("get的值是" + ValueObject.value);
                ValueObject.value = "";
                lock.notify();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private  int num = 0;


    public int getNum() {
        return num;
    }

    public synchronized void addNum() {
        this.num = num + 1;
    }

    public static void main(String[] args) {
        C c = new C();
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                c.addNum();
                System.out.println(c.getNum());
            }, "A" + i).start();
            countDownLatch.countDown();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("================");
        System.out.println(c.getNum());
    }


}
