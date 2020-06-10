package com.example.thread.stop;

public class StopInterruptedThread {

    public static void main(String[] args) throws InterruptedException {
        StopInterruptedThread stopInterruptedThread = new StopInterruptedThread();

        stopInterruptedThread.wrongStopThreadByInterrupt();


//        stopInterruptedThread.rightStopThreadByInterrupt();

    }


    public void rightStopThreadByInterrupt() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            int count = 0;
            while (!Thread.currentThread().isInterrupted() && count < 1000) {
                System.out.println(count);
                count++;
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    //如果不重置中的标志位，会导致线程无法正确停止
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }

        });
        t1.start();
        Thread.sleep(5);
        t1.interrupt();
    }


    public void wrongStopThreadByInterrupt() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            int count = 0;
            while (!Thread.currentThread().isInterrupted() && count < 1000) {
                System.out.println(count);
                count++;
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    //如果不重置中的标志位，会导致线程无法正确停止
//                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }

        });
        t1.start();
        Thread.sleep(5);
        t1.interrupt();
    }

}
