package com.example.Leetcode;

/**
 * 找到环中的第一个节点（leetcode 142）
 *
 * @author xuyangyang
 */
public class DetectCycle {

    public ListNode detectCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = head;
                while (fast != slow) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }

        }
        return null;
    }
}
