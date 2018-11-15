package com.example.datastructure;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import sun.security.util.ByteArrayLexOrder;

import java.util.ArrayList;

public class XArray {

    private int[] data;

    //定义数组长度
    private int n = 0;

    //定义数组实际个数
    private int count = 0;

    public XArray() {
        this.data = new int[10];
        this.n = 10;
        this.count = 0;
    }

    public XArray(int size) {
        this.data = new int[size];
        this.n = size;
        this.count = 0;
    }


    public void insert(int value) {

        ensureCapacity(count);
        data[count] = value;
    }

    private void ensureCapacity(int length) {
        if (count == n) {
            //扩容
        }
    }

    public boolean insert(int index, int value) {

        if (count == n) {
            System.out.println("空间已满");
            return false;
        }

        if (index < 0 || index > count) {
            System.out.println("数组下标不合法");
            return false;
        }
        for (int i = count; i > index; --i) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        ++count;
        return true;
    }


    public boolean delete(int index) {

        if (index < 0 || index >= count) {
            System.out.println("数组下标不合法");
            return false;
        }

        for (int i = index + 1; i < count; i++) {
            data[i - 1] = data[i];
        }
        --count;
        return true;

    }


    public void printAll() {
        for (int i = 0; i < count; i++) {
            System.out.println(data[i] + "");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        XArray array = new XArray(5);
        array.printAll();
        array.insert(0, 3);
        array.insert(0, 4);
        array.insert(1, 5);
        array.insert(3, 9);
        array.insert(3, 10);
//        array.insert(3, 11);
        array.printAll();
    }



}
