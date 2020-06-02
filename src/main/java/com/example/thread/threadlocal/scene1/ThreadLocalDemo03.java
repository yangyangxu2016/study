package com.example.thread.threadlocal.scene1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 1000个线程测试 1000 个任务对应 1000 个 simpleDateFormat 对象
 *
 * @author xuyy
 */
public class ThreadLocalDemo03 {

    public static ExecutorService threadPoll = new ThreadPoolExecutor(16, 16,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(1000));


    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPoll.submit(() -> {
                ThreadLocalUtil.task(finalI);
            });
        }
        threadPoll.shutdown();

    }

}


