package com.example.thread.threadlocal.scene1;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 加 ThreadLocal 解决
 * ThreadLocal 用作保存每个线程独享的对象，为每个线程都创建一个副本，每个线程都只能修改自己所拥有的副本,
 * 而不会影响其他线程的副本，这样就让原本在并发情况下，线程不安全的情况变成了线程安全的情况。
 *
 * @author xuyy
 */
public class ThreadLocalDemo06 {

    public static ExecutorService threadPoll = new ThreadPoolExecutor(16, 16,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(1000));


    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPoll.submit(() -> {
                date(finalI);
            });
        }
        threadPoll.shutdown();

    }


    public static String date(int seconds) {
        Date date = new Date(1000 * seconds);
        String format = ThreadSafeFormat.dataFormatterThreadLocal.get().format(date);
        System.out.println(System.identityHashCode(format));
        return format;
    }


}


