package com.example.Leetcode;

import java.util.Arrays;

/**
 * @author xuyangyang
 */
public class Sort {


    public static void main(String[] args) {
        int[] a = {4, 5, 6, 1, 2, 3};
        System.out.println("===待排序数据: {4, 5, 6, 1, 2, 3}===========");
        System.out.println();


        BubbleSort(a);
        System.out.println("===========冒泡排序=================");
        System.out.println(Arrays.toString(a));
        System.out.println();

        int[] b = {4, 5, 6, 1, 2, 3};
        insertSort(b);
        System.out.println("===========插入排序================");
        System.out.println(Arrays.toString(b));
        System.out.println();

        int[] c = {4, 5, 6, 1, 2, 3};
        selectionSort(c);
        System.out.println("===========选择排序================");
        System.out.println(Arrays.toString(c));
        System.out.println();

        int[] d = {4, 5, 6, 1, 2, 3};
        mergeSort(d);
        System.out.println("===========归并排序================");
        System.out.println(Arrays.toString(d));
        System.out.println();

        int[] e = {4, 5, 6, 1, 2, 3};
        quickSort(e, 0, e.length - 1);
        System.out.println("===========归并排序================");
        System.out.println(Arrays.toString(e));
        System.out.println();
    }


    private static void selectionSort(int[] a) {

        for (int i = 0; i < a.length; i++) {
            int value = a[i];
            int index = i;
            for (int j = i; j < a.length; j++) {
                if (a[j] < value) {
                    value = a[j];
                    index = j;
                }
            }
            int temp = a[i];
            a[i] = value;
            a[index] = temp;

        }


    }

    /**
     * @param a
     * @return
     */
    private static int[] insertSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int value = a[i];
            int j = 0;
            for (j = i - 1; j >= 0; j--) {
                if (a[j] > value) {
                    a[j + 1] = a[j];

                } else {
                    break;
                }
            }
            a[j + 1] = value;
        }


        return a;
    }

    private static int[] BubbleSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            boolean flag = false;
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    a[j] ^= a[j + 1];
                    a[j + 1] ^= a[j];
                    a[j] ^= a[j + 1];
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }

        return a;
    }


    public static void mergeSort(int[] a) {
        mergeSort(a, 0, a.length - 1);
    }

    private static void mergeSort(int[] a, int left, int right) {
//        递归终止条件
        if (left < right) {
//          寻找中点
            int mid = (left + right) / 2;
//          左边数组
            mergeSort(a, left, mid);
//          右边数组
            mergeSort(a, mid + 1, right);
//          合并数组
            merge(a, left, mid, right);
        }
    }

    private static void merge(int[] a, int left, int mid, int right) {
//         申请临时数组
        int[] temp = new int[right - left + 1];

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
//      拷贝剩余数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }

        while (j <= right) {
            temp[k++] = a[j++];
        }

        k = 0;
        while (left <= right) {
            a[left++] = temp[k++];
        }
    }


    private static void quickSort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int q = parition(a, left, right);
        quickSort(a, left, q - 1);
        quickSort(a, q + 1, right);
    }

    private static int parition(int[] a, int left, int right) {
        int i = left;
        int prvot = right;
        for (int j = left; j <= right - 1; j++) {
            if (a[j] < a[prvot]) {
                int temp = a[j];
                a[j] = a[i];
                a[i] = temp;
                i = i + 1;
            }
        }
        int temp = a[prvot];
        a[prvot] = a[i];
        a[i] = temp;
        return i;
    }

}
