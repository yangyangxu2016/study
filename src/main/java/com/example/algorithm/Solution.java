package com.example.algorithm;

class Solution {

    public  static int fib(int n) {
        int constant = 1000000007;

        if (n < 2) {
            return n;
        }

        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;

        for (int i = 2; i <=n; i++) {
            nums[i] = (nums[i - 1] + nums[i - 2]) % 1000000007;
        }

        return nums[n];
    }

    public static void main(String[] args) {
        System.out.println(fib(0));
    }

}