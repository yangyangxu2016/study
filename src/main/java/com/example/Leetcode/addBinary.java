package com.example.Leetcode;

/**
 * @author xuyangyang
 */
public class addBinary {
    public static String addBinary(String a, String b) {
        // 取最大长度
        int len1 = a.length();
        int len2 = b.length();
        int maxNum = Math.max(len1, len2);
        //反转

        StringBuilder sb1 = new StringBuilder(a).reverse();
        StringBuilder sb2 = new StringBuilder(b).reverse();
        // 补0，保持和最大字符串一致
        while (sb1.length() < maxNum) {
            sb1.append("0");
        }
        while (sb2.length() < maxNum) {
            sb2.append("0");
        }
        //
        StringBuilder ret = new StringBuilder();
        int carry = 0;
        int num1;
        int num2;

        for (int i = 0; i < maxNum; i++) {
            num1 = sb1.charAt(i) - '0';
            num2 = sb2.charAt(i) - '0';
            if (num1 + num2 + carry > 1) {
                ret.append(num1 + num2 + carry - 2);
                carry = 1;
            } else {
                ret.append(num2 + num1+carry);
                carry = 0;
            }
        }
        //判断是否进位
        if (carry == 1) {
            ret.append("1");
        }
        return ret.reverse().toString();
    }

    public static void main(String[] args) {
        String a = "11", b = "1";
        String s = addBinary(a, b);
        System.out.println(s);
    }
}
