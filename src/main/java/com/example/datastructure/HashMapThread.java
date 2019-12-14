package com.example.datastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuyangyang
 */
public class HashMapThread extends Thread
{
    private static AtomicInteger ai = new AtomicInteger(0);
    private static Map<Integer, Integer> map = new HashMap<Integer, Integer>(1);

    @Override
    public void run()
    {
        while (ai.get() < 100000)
        {
            map.put(ai.get(), ai.get());
            System.out.println(ai.get());
            ai.incrementAndGet();
        }
    }
}