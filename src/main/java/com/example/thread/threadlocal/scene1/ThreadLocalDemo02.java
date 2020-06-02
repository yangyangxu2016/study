package com.example.thread.threadlocal.scene1;

/**
 * 10个线程测试 ， 10 个线程同时对应 10 个 SimpleDateFormat 对象
 *
 * @author xuyy
 */
public class ThreadLocalDemo02 {

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalDemo02 threadLocalDemo02 = new ThreadLocalDemo02();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                ThreadLocalUtil.task(finalI);
            }).start();
            Thread.sleep(100);
        }
    }


}


