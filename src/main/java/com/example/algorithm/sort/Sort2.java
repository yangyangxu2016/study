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
}
