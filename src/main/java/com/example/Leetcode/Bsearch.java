package com.example.Leetcode;

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
public class Bsearch {


    /**
     * 循环实现
     *
     * @param a
     * @param value
     * @return
     */
    private static int bSearchCircle(int[] a, int value) {
        int low = 0;
        int high = a.length - 1;

        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 递归实现
     *
     * @param a
     * @param value
     * @return
     */
    private static int bSearchRecursion(int[] a, int value) {

        return bSearchRecursion(a, 0, a.length - 1, 19);
    }

    private static int bSearchRecursion(int[] a, int low, int high, int value) {
        if (low > high) {
            return -1;
        }

        int mid = (high - low) / 2 + low;
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] > value) {
            return bSearchRecursion(a, low, mid - 1, value);
        } else {
            return bSearchRecursion(a, mid + 1, high, value);
        }
    }


    public static void main(String[] args) {
        int[] a = {8, 11, 19, 23, 27, 33, 45, 55, 67, 98};
        int binary = bSearchCircle(a, 19);
        int binary1 = bSearchRecursion(a, 19);
        System.out.println(binary);
        System.out.println(binary1);
        System.out.println(mySqrt(5));
    }

    public static float  mySqrt(int x) {
        float  mid = 0;

            float low = 0;
            float high = x;
            while (Math.abs(high - low) > 1e-6) {
                mid = (high - low) / 2 + low;
                float  temp = mid * mid;
                if ((temp - x) > 1e-6) {
                    high = mid;
                } else if ((temp - x) < 1e-6) {
                    low = mid;
                } else {
                    return mid;
                }
            }

        return mid;
    }


}
