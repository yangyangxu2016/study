package com.example.thread;

import java.util.concurrent.TimeUnit;

/**
 * 线程安全之可见性问题
 * <p>
 * java8 64位 默认运行的server模式，不可以修改为client，不加volatile将导致死循环，可以通过参数禁止JIT编译器 -Djava.compiler=NONE
 * 如果在32位模式默认在client运行，就不会有死循环
 * <p>
 * 可以添加volatile禁止指令重排序，可以解决死循环问题，因为JIT编译器优化了while（volatileTest.flag==true）为
 *
 * @<code>if(flag=true){ while(true){
 * }</code>
 * }
 */
public class VolatileTest {

    /**
     * 死循环
     */
    private boolean flag = true;

    /**
     * 不会死循环
     */
//    private volatile boolean flag = true;
    public static void main(String[] args) throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest();
        new Thread(() -> {
            int i = 0;
            // 这里JIT编译器会优化，不会每次都while判断，优化为if(volatileTest.flag=true){ while(true){}}
            while (volatileTest.flag) {
//                加锁也可以禁止编译优化和重排序
//                synchronized (VolatileTest.class) {
                i++;
//                }

            }
            System.out.println(i);
        }).start();

        TimeUnit.SECONDS.sleep(2);
        volatileTest.flag = false;
        System.out.println("线程将停止");
    }

}
