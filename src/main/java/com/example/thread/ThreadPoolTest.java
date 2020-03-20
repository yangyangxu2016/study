package com.example.thread;


import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Slf4j
@Controller
public class ThreadPoolTest {


    @GetMapping("/right")
    public int right() throws InterruptedException {

        AtomicInteger atomicInteger = new AtomicInteger();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5,
                5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadFactoryBuilder().setNameFormat("demoo-threadpool-%d").get(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        printStats(threadPoolExecutor);
        IntStream.rangeClosed(1,20).forEach(i->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int id = atomicInteger.incrementAndGet();

            try {
                threadPoolExecutor.submit(() -> {
                    log.info("{} started ", id);

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("{} finished", id);
                });
            } catch (Exception ex) {
                log.error("error sumitting task {}", id, ex);
                atomicInteger.decrementAndGet();
            }
        });

        TimeUnit.SECONDS.sleep(120);
        return atomicInteger.intValue();

    }

    private void printStats(ThreadPoolExecutor threadPoolExecutor) {
    }

    public static void main(String[] args) {
        Map<Long, String> map = new HashMap();
        System.out.println(map.get(1L));

    }


}
