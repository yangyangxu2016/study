package com.example.algorithm.sort;


import org.springframework.stereotype.Component;

/**
 * @Description: 排序
 * @Param:
 * @return:
 * @Author: 徐阳阳
 * @Date: 2020/3/24
 */
@Component
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

        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
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
            swap(a,i,min);
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

    private void merge(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        merge(a, left, mid);
        merge(a, mid + 1, right);
        mergeArray(a, left, mid, right);
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
        while (i <= mid) {
            tmp[k++] = a[i++];
        }
        while (j <= right) {
            tmp[k++] = a[j++];
        }

        // 临时数组搬移到原来数组
        k = 0;
        while (left <= right) {
            a[left++] = tmp[k++];
        }
    }

    /**
     * 快速排序，最坏O（N^2）,平均O(nlgn)
     * @param a
     */
    public void quickSort(int[] a) {

        int n = a.length;
        if (n <= 1) {
            return;
        }

        quick(a, 0, n - 1);

    }

    private void quick(int[] a, int left, int right) {

        if (left >= right) {
            return;
        }
        int q = parition(a, left, right);
        quick(a, left, q - 1);
        quick(a, q + 1, right);
    }

    /**
     * 分区函数，利用类似选择排序算法，原地分区
     * @param a
     * @param left
     * @param right
     * @return
     */
    private int parition(int[] a, int left, int right) {
        int prvot = a[right];
        int i = left;
        for (int j = left;j <= (right - 1);j++) {
            if (a[j] < prvot) {
                swap(a, i, j);
                i = i + 1;
            }
        }
        swap(a, i, right);
        return i;
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    /**
     * 分区函数，构建两个临时十足，占用空间大，不是原地排序算法
     * @param a
     * @param left
     * @param right
     * @return
     */
    private int parition2(int[] a, int left, int right) {

        int prvot = right;
        int start = left;

        //复制到两个数据
        int[] minTmp = new int[right + left];
        int[] maxTmp = new int[right + left];

        int min = 0;
        int max = 0;
        //这里要是<,不能用<=  排除选择的分区元素
        while (start < right) {
            if (a[prvot] > a[start]) {
                minTmp[min++] = a[start++];
            } else {
                maxTmp[max++] = a[start++];
            }
        }

        // 合并转移到原数组
        int k = 0;
        while (k < min) {
            a[left++] = minTmp[k++];
        }
        a[left++] = a[prvot];
        k = 0;
        while (k < max) {
            a[left++] = maxTmp[k++];
        }
        return prvot;
    }

}