package com.example.algorithm;

public class FnTest {



    public static int fib(int n){

        if (n<=0){
            return 0;
        }
        if ((n==1 || n==2)){
            return 1;
        }
        return fib(n-1)+fib(n-2);
    }


    /**
     * 0 1 2 3 5 8 13 21
     * @param n
     * @return
     */
    public static int fib1(int n){

        if (n<=0){
            return 0;
        }
        if ((n == 1 || n==2)){
            return 1;
        }

        int pre =1;
        int curr=1;
        int next =0;

        for (int i = 3; i <= n ; i++) {
            next=curr+pre;
            pre=curr;
            curr=next;
        }

        return next;
    }
    public static void main(String[] args) {
        System.out.println(fib(8));
        System.out.println(fib1(8));
    }
}
