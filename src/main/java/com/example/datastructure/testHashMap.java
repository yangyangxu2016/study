package com.example.datastructure;

/**
 * @author xuyangyang
 */
public class testHashMap {

    public static void main(String[] args) {
        HashMapThread hmt0 = new HashMapThread();
        HashMapThread hmt1 = new HashMapThread();
        HashMapThread hmt2 = new HashMapThread();
        HashMapThread hmt3 = new HashMapThread();
        HashMapThread hmt4 = new HashMapThread();
        int size=0;
        while (size<10000) {
            new HashMapThread().start();
            size++;
            System.out.println(size);
        }

    }
}
