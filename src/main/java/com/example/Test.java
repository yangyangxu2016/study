package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Test {


//    1.

    public static void main(String[] args) {
        //1. 字符串操作
        String input = "how are you ";
        String ret1 = transString(input);
        System.out.println("1: " + ret1);

        //2. 链表操作




        //3. 爬楼梯
        Map<Integer, Integer> map = new HashMap<>();
        Integer count = climbStairsOfCount(18, map);
        System.out.println("3:共有 " + count + "  爬楼梯方式");


    }

    static class Node{
        int  data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }




    }


    private static String transString(String input) {

        if (input == null) {
            return "";
        }
        String[] strArray = input.split(" ");
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < strArray.length; i++) {
            if (i != strArray.length - 1) {
                ret.append(strArray[i]);
                ret.append("%20");
            } else {
                ret.append(strArray[i]);
            }
        }
        return ret.toString();
    }


    private static Integer climbStairsOfCount(Integer num, Map<Integer, Integer> map) {
        if (num < 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }
        if (num == 2) {
            return 2;
        }
        if (num == 3) {
            return 3;
        }

        if (map.containsKey(num)){
            return map.get(num);
        }

        int ret =climbStairsOfCount(num - 1,map) + climbStairsOfCount(num - 2,map)
                + climbStairsOfCount(num - 3,map);

        map.put(num,ret);
        return ret;
    }

}
