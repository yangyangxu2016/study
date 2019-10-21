package com.example.linked;

/**
 * @author xuyangyang
 */
public class HuiWenStr {
    /**
     * 判断字符串是不是回文
     *
     * @param str
     * @return
     */
    public static boolean isHuiWei(String str) {
//        1. 定义初始化游标i=0,j=len-1
        int i = 0;
        int j = str.length() - 1;
//      字符串转为数字，方便寻址
        String[] strArr = str.split("");
//        置标志位为，
        Boolean flag = true;
        //开始比较，结束条件i《=j，i向前寻址，j向后寻址，每次比较，如果不等，修改标志位退出
        // 一直到两者相遇退出
        for (; i <= j; i++, j--) {
            if (!strArr[i].equals(strArr[j])) {
                flag = false;
                break;
            }
        }
        return flag;
    }


    /**
     * ·回文数的定义：
     * <p>
     * 对于非负数 其左右两边完全相同 则是回文。 e.g: 121 11 等
     * <p>
     * 对于负数 其绝对值左右两边完全相同 则是回文。 e.g: -121 -11 等
     *
     * @param args
     */
    public static void main(String[] args) {
        String str1 = "level";
        System.out.println(isHuiWei(str1));

        String str2 = "levvel";
        System.out.println(isHuiWei(str2));

        String str3 = "levell";
        System.out.println(isHuiWei(str3));
    }


}


