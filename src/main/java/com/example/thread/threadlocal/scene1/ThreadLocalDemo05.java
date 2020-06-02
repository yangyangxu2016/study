package com.example.thread.threadlocal.scene1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 加 synchronized 解决
 *
 * @author xuyy
 */
public class ThreadLocalDemo05 {

    public static ExecutorService threadPoll = new ThreadPoolExecutor(16, 16,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(1000));
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

    public static void main(String[] args) throws InterruptedException {
        long st = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1000);

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPoll.submit(() -> {
                ThreadLocalDemo05.task(simpleDateFormat, finalI);
                countDownLatch.countDown();
            });

        }
        threadPoll.shutdown();
        countDownLatch.await();
        long et = System.currentTimeMillis();
        System.out.println("耗时：" + (et - st) + "秒");
    }

    public static void task(SimpleDateFormat sdf, int second) {
        String date = ThreadLocalDemo05.date(sdf, second);
        System.out.println(date);
    }


    public   static String date(SimpleDateFormat sdf, int seconds) {
        Date date = new Date(1000 * seconds);
        String format = null;
        synchronized (ThreadLocalDemo05.class) {
            format = sdf.format(date);
        }
        return format;


    }

}


