package com.example.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class CyclicBrrierTest {


    BlockingQueue<Integer> pos = new LinkedBlockingQueue<>();
    BlockingQueue<Integer> dos = new LinkedBlockingQueue <>();

    volatile Integer i = 0;
    volatile Integer j = 0;

    Executor executor = Executors.newFixedThreadPool(1);

    final CyclicBarrier barrier = new CyclicBarrier(2, () -> {
        executor.execute(this::check);
    });

    void check() {
        Integer p = (Integer) pos.remove();
        Integer d = (Integer) dos.remove();
        log.info("开始对账：p={},d={}", p, d);
        Integer diff = check(p, d);
        save(diff);
    }

    private void save(Integer diff) {
        log.info("save-{}-保存成功", diff);
    }

    private Integer check(Integer p, Integer d) {
        log.info("check-，{} ：{}", p, d);
        return p + d;
    }

    void checkAll() {
        new Thread(() -> {
            while (true) {
                try {
                    pos.put(i++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    dos.put(j++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new CyclicBrrierTest().checkAll();
    }
}
