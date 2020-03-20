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

    public static void main(String[] args) {
            String loginCode="33e5b1d28f5c6333d8f9058e87fa3b2955efa212d64f2f23627f7b2795a568009964094a2bb17c952e422d077084a1b379fcc40508758e28f7a3096bebdce5ad";
            int codelen = loginCode.getBytes().length;
            System.out.println(codelen);

    }

}
