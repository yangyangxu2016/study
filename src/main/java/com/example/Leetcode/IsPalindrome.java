package com.example.Leetcode;

/**
 * 判断一个链表是否是回文 LeetCode-234
 *
 * @author xuyangyang
 */
public class IsPalindrome {
    /**
     * 判断一个链表是否是回文 快慢指针
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

//    1.利用快慢指针找到链表中点（后半链表的头结点）
//     链表长度为n，后半链表的头结点为(n+1)/2+1
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
//       2.反转后半部分链表
        ListNode reverseHead = reverseList(slow);

        while (head != null && reverseHead != null) {
            if (head.var != reverseHead.var) {
                return false;
            }
            head = head.next;
            reverseHead = reverseHead.next;
        }
        return true;
    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = null;
        while (head != null) {
            ListNode tempListNode = head.next;
            head.next = p;
            p = head;
            head = tempListNode;
        }
        return p;
    }
}
