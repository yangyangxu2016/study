package com.example.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuyangyang
 */
public class BlockedQueue {

    final Lock lock = new ReentrantLock();

    /**
     * 条件变量：队列不满
     */
    final Condition notFull = lock.newCondition();

    /**
     * 条件变量：队列不空
     */
    final Condition notEmpty = lock.newCondition();


    private List data = new ArrayList(100);

    /**
     * 进队
     *
     * @param value
     */
    public void enq(Object value) {
        lock.lock();

        try {

            while (isFull()) {
                notFull.await();
            }

            data.add(value);

            notEmpty.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 出队
     *
     * @return
     */
    public Object deq() {
        lock.lock();
        try {
            while (isEmpty()) {
                notEmpty.await();
            }
            notFull.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }


    public boolean isFull() {
        return data.size() == 100;
    }

    public boolean isEmpty() {
        return data.size() == 0;
    }


    public static void main(String[] args) {

        Optional<String> gender = Optional.of("MALE");
        String answer1 = "Yes";
        String answer2 = null;

        System.out.println("Non-Empty Optional:" + gender);
        System.out.println("Non-Empty Optional: Gender value : " + gender.get());
        System.out.println("Empty Optional: " + Optional.empty());

        System.out.println("ofNullable on Non-Empty Optional: " + Optional.ofNullable(answer1));
        System.out.println("ofNullable on Empty Optional: " + Optional.ofNullable(answer2));

        // java.lang.NullPointerException
//        System.out.println("ofNullable on Non-Empty Optional: " + Optional.of(answer2));

    }

}

