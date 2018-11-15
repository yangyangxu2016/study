package com.example.thread;

public class ThreadA extends Thread {

    public Object object;

    public ThreadA(Object object) {
        super();
        this.object = object;
    }


    @Override
    public void run() {
        synchronized (object) {
            if (MyList.size() != 5) {
                System.out.println("beginA "+System.currentTimeMillis());

                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("end A"+System.currentTimeMillis());
            }
        }
    }
}
