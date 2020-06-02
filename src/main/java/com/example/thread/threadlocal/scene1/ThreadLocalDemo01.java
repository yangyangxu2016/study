package com.example.thread.threadlocal.scene1;

/**
 * 1个线程测试
 *
 * @author xuyy
 */
public class ThreadLocalDemo01 {

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> ThreadLocalUtil.task(1)).start();

        Thread.sleep(1000);

        new Thread(() -> ThreadLocalUtil.task(2)).start();

    }

}


