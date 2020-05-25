package com.example.algorithm.sort;

import org.springframework.stereotype.Component;

@Component
public class Sort2 {


    /**
     * @param a
     */
    public void quickSort(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return;
        }
        quickSort(a, 0, n-1);
    }

    private void quickSort(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        // 分区函数
        int q = partition(a, p, r);

        quickSort(a, p, q - 1);
        quickSort(a, q + 1, r);
    }

    private int partition(int[] a, int p, int r) {
        int pivot = r;
        int i = p;

        for (int j = p; j <= r - 1; j++) {
            if (a[j] < a[pivot]) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, i, pivot);
        return i;
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public int[] bubbleSort(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }
        int n = nums.length;

        for (int i = 0; i < n; ++i) {
            boolean flag = false;
            for (int j = 0; j < n - 1 - i; ++j) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        return nums;
    }


    public int[] selectSort(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }
        int n = nums.length;

        //选择排序，从0开始选择最小数据，依次插入，

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
        return nums;
    }


    public int[] insertSort(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }
        int n = nums.length;

        //像扑克牌一样，依次选取下一个元素，维护一个有序空间，插入有序空间最小的，就像插入数组一样
        for (int i = 1; i < n; i++) {
            int value = nums[i];
            int j = i-1;
            for (; j >= 0; --j) {
                if (value < nums[j]) {
                    nums[j + 1] = nums[j];
                }else {
                    break;
                }
            }
            nums[j + 1] = value;



        }

        return nums;

    }

}
