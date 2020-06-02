package com.example.thread.threadlocal.scene1;

import java.text.SimpleDateFormat;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 只用一个 simpleDateFormat 对象 线程不安全
 *
 * @author xuyy
 */
public class ThreadLocalDemo04 {

    public static ExecutorService threadPoll = new ThreadPoolExecutor(16, 16,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(1000));
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPoll.submit(() -> {
                ThreadLocalUtil.task(simpleDateFormat,finalI);
            });
        }
        threadPoll.shutdown();

    }


}


