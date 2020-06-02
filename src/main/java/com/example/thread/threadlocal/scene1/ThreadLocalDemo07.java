package com.example.thread.threadlocal.scene1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 加 ThreadLocal 未解决
 * <p>
 * {@link #simpleDateFormat} 变为共享资源 ，加{@link ThreadLocal} 无用
 *
 * @author xuyy
 */
public class ThreadLocalDemo07 {

    public static ExecutorService threadPoll = new ThreadPoolExecutor(16, 16,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(1000));


    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPoll.submit(() -> {
                String date = new ThreadLocalDemo07().date(finalI);
                System.out.println(date);
            });
        }
        threadPoll.shutdown();

    }


    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = ThreadSafeFormatter07.simpleDateFormatThreadLocal.get();
        System.out.println(System.identityHashCode(simpleDateFormat));
        return simpleDateFormat.format(date);
    }

    static class ThreadSafeFormatter07 {
        public static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
            @Override
            protected SimpleDateFormat initialValue() {
                return ThreadLocalDemo07.simpleDateFormat;
            }

        };
    }


}


