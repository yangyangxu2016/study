package com.example.Leetcode;

/**
 * 给定一个链表，判断链表中是否有环。 （leetcode 141）
 * https://leetcode-cn.com/explore/learn/card/linked-list/194/two-pointer-technique/744/
 *
 * @author xuyangyang
 */
public class HasCycle {


    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
