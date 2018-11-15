package com.example.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现生产者 / 消费者模式 : 一对一交替打印
 */
public class MyService2 {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private boolean hasValue = false;

    public void setValue() {
        try {
            lock.lock();
            while (hasValue == true) {
                condition.await();
            }

            System.out.println("消费 * ");
            hasValue = true;
            condition.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void getValue() {
        try {
            lock.lock();
            while (hasValue == false) {
                condition.await();
            }
            System.out.println("生产 *");
            hasValue = false;
            condition.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    Runnable runnableA = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                setValue();
            }
        }
    };
    Runnable runnableB = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                getValue();
            }
        }
    };


    public static void main(String[] args) {
        MyService2 service2 = new MyService2();

        Thread a = new Thread(service2.runnableA);
        a.start();
        Thread b = new Thread(service2.runnableB);
        b.start();
//      运行结果
//                消费 *
//                生产 *
//                消费 *
//                生产 *
//                消费 *
//                生产 *
//                消费 *
//                生产 *
    }
}

