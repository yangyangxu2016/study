package com.example.Leetcode;

/**
 * 删除链表倒数第n个节点，并返回链表的头结点  快慢指针
 *
 * @author xuyangyang
 */
public class RemoveNthFromEnd {

    /**
     * 删除链表倒数第n个节点，并返回链表的头结点  快慢指针法
     * <p>
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * <p>
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     *
     * @param head
     * @return
     */
    public XListNode removeNthFromEnd(XListNode head, int n) {
        XListNode slow = head;
        XListNode fast = head;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        if (fast == null) {
            return slow.next;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return head;
    }

    /**
     * 删除链表倒数第n个节点，并返回链表的头结点
     *
     * @param head
     * @param n
     * @return
     */
    public XListNode removeNthFromEnd2(XListNode head, int n) {
        if (head == null) {
            return null;
        }

        int total = 0;
        XListNode curListNode = head;
        while (curListNode != null) {
            total++;
            curListNode = curListNode.next;
        }

        if (n > total || n < 0) {
            System.out.println("n is invalid");
            return head;
        }

        int index = total - n;
        if (index == 0) {
            return head.next;
        }

        curListNode = head;
        XListNode preListNode = head;
        for (int i = 0; i < index; i++) {
            preListNode = curListNode;
            curListNode = curListNode.next;
        }

        preListNode.next = curListNode.next;

        return head;
    }
}
