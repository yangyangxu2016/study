package com.example.thread.threadlocal.scene1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 加 ThreadLocal 解决
 * 关闭线程池方法 {@link ExecutorService awaitTermination}
 * @author xuyy
 */
public class ThreadLocalDemo08 {

    public static ExecutorService threadPoll = new ThreadPoolExecutor(16, 16,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(1000));

    public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");


    public static void main(String[] args) throws InterruptedException {
        long st = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPoll.execute(() -> {
                String date = date(finalI);
                System.out.println(date);
            });
        }
        threadPoll.shutdown();
        threadPoll.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        if (threadPoll.isShutdown()) {
            long et = System.currentTimeMillis();
            System.out.println("耗时：" + (et - st) + "秒");
        }
    }


    public static String date(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = ThreadSafeFormat.dataFormatterThreadLocal.get();
        System.out.println(System.identityHashCode(simpleDateFormat));
        return simpleDateFormat.format(date);
    }

    static class ThreadSafeFormatter06 {
        public static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
            @Override
            protected SimpleDateFormat initialValue() {
                return new ThreadLocalDemo08().simpleDateFormat;
            }

        };
    }
}


