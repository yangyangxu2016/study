package com.example.code;

/**
 * 1. 编译为class文件
 * javac Foo.java
 * 2. 使用 javap 来查阅 Foo.test 方法的字节码。
 * javap -p -v Foo >foo.text
 */
public class Foo {
    private int tryBlock;
    private int catchBlock;
    private int finallyBlock;
    private int methodExit;

    public void test() {
        try {
            tryBlock = 0;
        } catch (Exception e) {
            catchBlock = 1;
        } finally {
            finallyBlock = 2;
        }
        methodExit = 3;
    }
}
