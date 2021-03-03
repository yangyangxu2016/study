package com.example.java8;

import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * todo
 * date：2021/2/27  11:09 上午
 *
 * @author xuyy
 */
@Slf4j
public class ExceptionHanderDemo {


    public static void main(String[] args) throws Exception {
        ExceptionHanderDemo demo = new ExceptionHanderDemo();
        demo.threadExecuteException();
        //handerFinallyException3();
        int heart = 220, age = 29, calmHeart = 59;
        double low = 0.59, high = 0.74;
        double a = (heart - age - calmHeart) * low + calmHeart;
        double b = (heart - age - calmHeart) * high + calmHeart;
        System.out.println("a:" + a + "b:" + b);

    }


    public static void handerFinallyException1() {

        try {
            int b = 1 / 0;
        } finally {
            log.info("finally:{}", "FIND");
            throw new RuntimeException("fin");
        }
    }


    public static void handerFinallyException2() {
        try {
            int b = 1 / 0;
        } finally {
            try {
                throw new RuntimeException("fin");
            } catch (Exception exception) {
                log.info("finally:{}", "FIND");
            }
        }
    }

    public static void handerFinallyException3() throws Exception {
        Exception e = null;
        try {
            int b = 1 / 0;
        } catch (Exception ex) {
            e = ex;
        } finally {
            try {
                throw new RuntimeException("fin");
            } catch (Exception exception) {
                log.info("finally:{}", "FIND");
                if (e != null) {
                    e.addSuppressed(exception);
                } else {
                    e = exception;
                }
            }
        }
        throw e;
    }

    /**
     * 1、不要把异常吞了，要抛出原本异常
     * 2、如果需要转换，也不要抛弃原来的e信息
     */
    public static void throwEx() {
        try {
            int b = 20 / 0;
        } catch (Exception ex) {
            log.error("err1:{}", ex);
            log.error("err2:{}", ex.getMessage());
            log.error("err4:{}", ex.getCause());
            log.error("err5:{}", ex.getLocalizedMessage());
        }
    }

    public void method1() {

    }

    /**
     * 主动抛出
     * 需要手动捕获异常，不然当前线程死亡，会新创建一个线程
     */
    public void threadExecuteException() {

        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("prefix-" + "%d")
                .setUncaughtExceptionHandler((thread, throwble) -> {
                    log.error("t:{},th:{}", thread, throwble);
                })
                .get();
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS
                , new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        IntStream.rangeClosed(1, 10).forEach(i -> {
            executorService.execute(() -> {
                log.info("thread: " + i);
                if (i == 5) {
                    //throwEx();
                    int b = 20 / 0;
                }
            });
        });
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 需要在 submit.get(); 获取捕获，否则不会抛出
     */
    public void threadSubmitException() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<?> submit = executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                log.info("thread: " + i);
                if (i == 5) {
                    throwEx();
                }
            }
        });
        try {
            submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
