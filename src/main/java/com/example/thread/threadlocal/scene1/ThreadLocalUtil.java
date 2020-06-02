package com.example.thread.threadlocal.scene1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalUtil {

    public static void task(int second) {
        String date = ThreadLocalUtil.date(second);
        System.out.println(date);
    }

    public  static void task(SimpleDateFormat sdf, int second) {
        String date = ThreadLocalUtil.date(sdf, second);
        System.out.println(date);
    }


    public   static String date(SimpleDateFormat sdf, int seconds) {
        Date date = new Date(1000 * seconds);
        return sdf.format(date);
    }

    public  static String date(int seconds) {
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        return simpleDateFormat.format(date);
    }
}
