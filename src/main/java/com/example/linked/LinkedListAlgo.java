package com.example.linked;

/**
 * 1) 单链表反转:
 * 2）两个有序的链表合并
 * 3）求链表的中间节点
 *
 * @author xuyangyang
 */
public class LinkedListAlgo {


    /**
     * 单链表反转 循环 leetcode 206
     *
     * @param head
     * @return
     */
    public static Node reverseByLoop(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node preNode = null;
        Node curr = head;
        while (curr != null) {
            Node tempNode = curr.next;
            curr.next = preNode;
            preNode = curr;
            curr = tempNode;
        }
        return preNode;
    }


    /**
     * 反转单链表 递归 leetcode 206
     *
     * @param head
     * @return
     */
    public static Node reverseByRecursion(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node newHead = reverseByRecursion(head.next);//先反转后面的链表，从最后面的两个节点开始反转，以此向前

        head.next.next = head;//将后一个节点执行前一个节点
        head.next = null;//将链表中前一个节点执行后一个节点中的关系断开
        return newHead;
    }


    /**
     * 判断链表是否有环（leetcode 141）
     *
     * @param head
     * @return
     */
    public boolean hasCycle(Node head) {
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 找到环中的第一个节点（leetcode 142）
     *
     * @param head
     * @return
     */
    public Node detectCycle(Node head) {

        Node slow = head;
        Node fast = head;
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


    static class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {

    }


}
