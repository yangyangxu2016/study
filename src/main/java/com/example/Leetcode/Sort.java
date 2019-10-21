package com.example.Leetcode;

/**
 * @author xuyangyang
 */
public class Sort {


    public static void main(String[] args) {
        int[] a = {4, 5, 6, 1, 2, 3};
        System.out.println("===待排序数据: {4, 5, 6, 1, 2, 3}===========");
        System.out.println();
        int[] intsa = BubbleSort(a);
        System.out.println("===========冒泡排序=================");
        for (int i : intsa) {
            System.out.print(i);
            System.out.print(" ");
        }

        int[] b = {4, 5, 6, 1, 2, 3};
        int[] intsb = insertSort(b);
        System.out.println();
        System.out.println("===========插入排序================");
        System.out.println();
        for (int i : intsb) {
            System.out.print(i);
            System.out.print(" ");
        }
        int[] c = {4, 5, 6, 1, 2, 3};
        int[] intsc = selectionSort(c);
        System.out.println();
        System.out.println("===========插入排序================");
        System.out.println();
        for (int i : intsc) {
            System.out.print(i);
            System.out.print(" ");
        }

    }

    private static int[] selectionSort(int[] a) {

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

        return a;
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


}
