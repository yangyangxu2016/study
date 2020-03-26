package com.example.algorithm.sort;


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


    /**
     * @param a :
     * @Description : 选择排序
     * @Return : void
     * @Author :徐阳阳
     * @Date: 2020/3/25
     */
    public void selectSort(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return;
        }

        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i; j < n; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            if (i != min) {
                int temp = a[i];
                a[i] = a[min];
                a[min] = temp;
            }
        }

    }

    /**
     * @Description : 归并排序
     * @params :
     * @Return :
     * @Author :徐阳阳
     * @Date: 2020/3/25
     */
    public void mergeSort(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return;
        }
        merge(a, 0, n - 1);
    }

    private void merge(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = (p + r) / 2;
        merge(a, p, q);
        merge(a, q + 1, r);
        mergeArray(a, p, q, r);
    }


    /**
     * 合并数组
     *
     * @param a 数组a
     * @param left
     * @param mid
     * @param right
     */
    private void mergeArray(int[] a, int left, int mid, int right) {


        // 遍历比较两个数组相对较小的，放入临时数组
        int[] tmp = new int[a.length];
        int k = 0;
        int i = left;
        int j = mid + 1;
        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
            }
        }

        // 把剩余数组合并进去
        while (i <= left) {
            tmp[k++] = a[i++];
        }
        while (j <= right) {
            tmp[k++] = a[j++];
        }

        // 临时数组搬移到原来数组
        k = 0;
        while (left < right) {
            a[left++] = tmp[k++];
        }
    }

    public void quickSort(int [] a){


    }

}
