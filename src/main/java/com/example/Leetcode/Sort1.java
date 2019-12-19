package com.example.Leetcode;

import org.apache.commons.io.FileUtils;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuyangyang
 */
public class Sort1 {


    public static void bubbleSort(int[] a) {
        if (a == null) {
            return;
        }
        int n = a.length;
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < n - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
//                swap(a, j);
                if (a[j] > a[j + 1]) {
                    int temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                    flag = true;
                }
            }
            System.out.println("第" + count.incrementAndGet() + "次循环");
            if (!flag) {
                System.out.println("=============");
                break;
            }
        }
    }


    public static void selectSort(int[] a) {
        if (a == null) {
            return;
        }
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            int value = a[i];
            for (int j = i; j < n - 1; j++) {
                if (a[j] < value) {
                    value = a[j];
                    min = j;
                }
            }
            int temp = a[i];
            a[i] = value;
            a[min] = temp;
        }
    }

    public static void insertBubble(int a[]) {
        if (a == null || a.length == 1) {
            return;
        }
        int n = a.length;
        for (int i = 1; i < n; i++) {
            int value = a[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (a[j] > value) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = value; // 插入数据
        }

    }


    private static void mergeBubble(int[] a) {

        if (a == null || a.length <= 1) {
            return;
        }
        int n = a.length;
        mesort(a, 0, n - 1);
//
    }

    //    { 1, 2, 3, 4 }
    private static void mesort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (right - left) / 2 + left;
        //对左边
        mesort(a, left, mid);
        System.out.println("左边");
        //对右边
        mesort(a, mid + 1, right);
        System.out.println("右边");
//        合并
        merge(a, left, mid, right);
    }

    private static void merge(int[] a, int left, int mid, int right) {
//        申请一个辅助数组
        int[] temp = new int[a.length];
//        合并有序数组
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        //判断哪个数组中有剩余数据
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        while (j <= right) {
            temp[k++] = a[j++];
        }

        //拷贝回原数组
        int index = 0;
        while (left <= right) {
            a[left++] = temp[index++];
        }
    }


    /**
     * https://github.com/wangzheng0822/algo/blob/master/java/12_sorts/QuickSort.java
     *
     * @param a
     */
    private static void quickSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        quick(a, 0, a.length - 1);
    }

    private static void quick(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        //获取分区点,
        int q = partition(a, p, r);
        quick(a, p, q - 1);
        quick(a, q + 1, r);

    }

    /**
     * 学习选择排序，分为已排序区间和未排序区间，
     *
     * @param a
     * @param p
     * @param r
     * @return
     */
    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for (int j = p; j < r; j++) {

            if (a[j] < pivot) {
                swap(a, i, j);
                i = i + 1;
            }
        }
        swap(a, i, r);
        System.out.println("i=" + i);
        return i;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args)  {
        int[] a = {6, 5, 4, 3, 2, 1};
//        bubbleSort(a);
//        Arrays.stream(a).forEach(x -> System.out.println(x));
//        selectSort(a);
//        insertBubble(a);
//        mergeBubble(a);
        quickSort(a);
        Arrays.stream(a).forEach(x -> System.out.println(x));




    }
}
