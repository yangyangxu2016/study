package com.example.thread.threadlocal.scene2;

/**
 * ThreadLocal 用作每个线程内需要独立保存信息的场景，供其他方法更方便得获取该信息，
 * * 每个线程获取到的信息都可能是不一样的，前面执行的方法设置了信息后
 * * 后续方法可以通过 ThreadLocal 直接获取到，避免了传参
 *
 * @author xuyy
 */
public class Scene2MainTest {

    public static void main(String[] args) {
        Service1.service1();
    }
}
