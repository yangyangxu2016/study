package com.example.thread.threadlocal.scene1;

import java.text.SimpleDateFormat;

public class ThreadSafeFormat {

    public static ThreadLocal<SimpleDateFormat> dataFormatterThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("mm:ss");
        }
    };

}
