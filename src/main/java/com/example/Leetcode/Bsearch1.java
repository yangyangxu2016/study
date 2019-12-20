package com.example.Leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * 二分查找，它的时间复杂度是 O(logn)。
 * <p>
 * 1. 首先，二分查找依赖的是顺序表结构，简单点说就是数组。
 * 2. 其次，二分查找针对的是有序数据。二分查找只能用在插入、删除操作不频繁，
 * 3. 再次，数据量太小不适合二分查找。
 * 4. 最后，数据量太大也不适合二分查找。二分查找的底层需要依赖数组这种数据结构，
 * 而数组为了支持随机访问的特性，要求内存空间连续，对内存的要求比较苛刻。
 *
 * @author xuyangyang
 */
@Slf4j
public class Bsearch1 {


    public static void main(String[] args) {
        int[] a = {8, 11, 19, 23, 27, 33, 45, 55, 67, 98};
        int[] b = {1, 3, 4, 5, 6, 8, 8, 8, 11, 18};
        log.info("循环二分：{}", bSearchCircle(a, 19));
        log.info("递归二分：{}", bSearchRecursion(a, 19));
//        log.info("查找最后一个小于等于给定值的元素 :  {}", bserachFristLess(b, 8));
        log.info("求一个数的平方根？ 要求精确到小数点后 6 位 :  {}", mySqrt(10));
    }

    /**
     * 求一个数的平方根？ 要求精确到小数点后 6 位
     *
     * @param value
     * @return
     */
    private static float mySqrt(int value) {
        return squt(value, 0, value);
    }

    private static float squt(float value, float low, float high) {
        if (low > high) {
            return -1;
        }

        float mid = (high - low) / 2 + low;

        String[] split = String.valueOf(mid).split(".");
        if (split.length > 1) {
            if (split[2].length() >= 6) {
                return mid;
            }
        }

        float x = mid * mid;
        if (x > value) {
            return squt(value, low, mid);
        } else if (x < value) {
            return squt(value, mid, high);
        } else {
            return mid;
        }
    }


    /**
     * 循环 二分查找不重复元素
     *
     * @param a
     * @param value
     * @return
     */
    private static int bSearchCircle(int[] a, int value) {
        int start = 0;
        int end = a.length - 1;

        while (start <= end) {
            int middle = (end - start) / 2 + start;
            if (value == a[middle]) {
                return a[middle];
            } else if (a[middle] < value) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return 0;
    }

    /**
     * 递归 二分查找不重复元素
     * 递归条件：f（n）=f(n/2)
     * 终止:p>r
     *
     * @param a
     * @param value
     * @return
     */
    private static int bSearchRecursion(int[] a, int value) {
        return searchRecusion(a, 0, a.length - 1, value);
    }

    private static int searchRecusion(int[] a, int low, int high, int value) {
        if (low > high) {
            return -1;
        }
        int mid = (high - low) / 2 + low;
        if (value == a[mid]) {
            return value;
        } else if (value < a[mid]) {
            return searchRecusion(a, low, mid - 1, value);
        } else {
            return searchRecusion(a, low + 1, high, value);
        }

    }

}
