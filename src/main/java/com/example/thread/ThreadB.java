package com.example.thread;

public class ThreadB extends Thread {

    public Object object;

    public ThreadB(Object object) {
        super();
        this.object = object;
    }


    @Override
    public void run() {
        synchronized (object) {
            for (int i = 0; i < 10; i++) {
                MyList.add();
                if (MyList.size() == 5) {
                    object.notify();
                    System.out.println("已发出通知");
                }
                System.out.println("添加了"+ (i+1)+"个元素");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
