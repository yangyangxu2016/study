package com.example.algorithm.sort;


import org.springframework.stereotype.Service;

/**
 * @Description: 排序
 * @Param:
 * @return:
 * @Author: 徐阳阳
 * @Date: 2020/3/24
 */
public class Sort {


    /**
     * @Description: 冒泡排序
     * @Param:
     * @return:
     * @Author: 徐阳阳
     * @Date: 2020/3/24
     */
    public void bubbleSort(int[] a) {
        if (a.length <= 1) {
            return;
        }
        int n = a.length;

        for (int i = 0; i < n ; i++) {
            boolean flag = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    //表示有数据交换
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }


    /**
     * @param a :
     * @Description : 插入排序
     * @Return : void
     * @Author :徐阳阳
     * @Date: 2020/3/24
     */

    public void insertSort(int[] a) {
        if (a.length <= 1) {
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

            a[j + 1] = value;
        }

    }


    public void quickSort(int [] a){}


}
