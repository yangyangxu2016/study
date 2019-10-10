package com.example.Leetcode;

/**
 * leetcode 206
 * https://github.com/yangyangxu2016/leetcode/blob/master/problems/206.reverse-linked-list.md
 *
 * @author xuyangyang
 */
public class ReverseLinkedList {


    /**
     * 单链表反转 循环 leetcode 206
     *
     * @param head
     * @return
     */
    public ListNode reverseByLoop(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode preListNode = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode tempListNode = curr.next;
            curr.next = preListNode;
            preListNode = curr;
            curr = tempListNode;
        }
        return preListNode;
    }


    /**
     * 单链表反转 递归
     *
     * @param head
     * @return
     */
    public ListNode reverseByRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //先反转后面的链表，从最后面的两个节点开始反转，以此向前
        ListNode newHead = reverseByRecursion(head.next);
        //将后一个节点执行前一个节点
        head.next.next = head;
        //将链表中前一个节点执行后一个节点中的关系断开
        head.next = null;
        return newHead;
    }


}
