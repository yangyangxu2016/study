package com.example.thread;

import java.util.ArrayList;
import java.util.List;

public class MyList {

    private static List list = new ArrayList();

    public static void add() {
        list.add("anyString");
    }

    public static int size() {
        return list.size();

    }

    static String lock = new String("");

    public static Runnable runnableA = new Runnable() {
        @Override
        public void run() {
            while (true) {
                new P(MyList.lock).setValue();
            }
        }
    };

    public static Runnable runnableB = new Runnable() {
        @Override
        public void run() {
            while (true) {
                new C(MyList.lock).getValue();

            }
        }
    };



}
