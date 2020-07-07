package com.example.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayTest {


    public static void main(String[] args) {

        int[] a = {1, 1, 3, 3, 4, 7, 8};

        //求数组中最多次数
        int maxCountOfElement = getMaxCount(a);
        System.out.println(maxCountOfElement);
        //反转一个数组
        int[] ints = invertedSequence(a);
        System.out.println(Arrays.toString(ints));
        int[] ints1 = invertedSequence1(a);
        System.out.println(Arrays.toString(ints1));
    }


    public static int getMaxCount(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(a).forEach(x -> {
            if (map.containsKey(x)) {
                map.put(x, map.get(x) + 1);
            } else {
                map.put(x, 1);
            }

        });

        int maxNmuber = 0;
        Integer[] max = map.values().toArray(new Integer[0]);

        int maxCount = 0;
        for (int i = 0; i < max.length; i++) {
            if (maxCount < max[i]) {
                maxCount = max[i];
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(maxCount)) {
                maxNmuber = entry.getKey();
            }
        }
        return maxNmuber;
    }

    /**
     * 时间复杂度： O  (n)
     * 空间复杂度： O (n
     *
     * @param a
     * @return
     */
    public static int[] invertedSequence(int[] a) {
        int[] temp = new int[a.length];
        int j = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            temp[j++] = a[i];
        }
        return temp;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O（1）
     *
     * @param a
     * @return
     */
    public static int[] invertedSequence1(int[] a) {
        int temp = 0;
        int n = a.length;
        for (int i = 0; i < (n / 2); i++) {
            temp = a[i];
            a[i] = a[n - 1 - i];
            a[n - 1 - i] = temp;
        }
        return a;
    }


}
