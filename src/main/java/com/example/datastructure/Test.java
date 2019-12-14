package com.example.datastructure;//当rpc结果返回之前，阻塞调用线程，让调用线程等待，
//当rpc返回结果后，唤醒调用线程，让调用线程重新执行

import javax.xml.ws.Response;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    //create lock and condition
    private final Lock lock = new ReentrantLock();

    private final Condition done = lock.newCondition();

    private Object response = new Object();

    //caller through this method wait result
    Object get(int timeout) throws TimeoutException {
        long start = System.nanoTime();
        lock.lock();
        try {
            while (!isDone()) {
                done.await(timeout, TimeUnit.SECONDS);
                long cur = System.nanoTime();
                if (isDone() || cur - start > timeout) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        if (!isDone()) {
            throw new TimeoutException();
        }

        return returnFromResponse();
    }

    private Object returnFromResponse() {
        return null;
    }


    //rpc结果是否返回
    private boolean isDone() {

        return response != null;
    }

// rpc结果返回时调用改方法

    private void doRecetved(Response res) {
        lock.lock();
        try {
            response = res;
            if (done != null) {
                done.signal();
            }
        } finally {
            lock.unlock();
        }
    }


}
