package com.example.linked;

/**
 * 1)单链表的插入、删除、查找操作
 * 2）链表中存储的是int类型的数据
 *
 * @author xuyangyang
 */
public class SinglyLinkedList {

    private Node head;

    public Node findByValue(int value) {
        Node p = head;
        while (p != null && p.data != value) {
            p = p.next;
        }
        return p;
    }

    public Node findByIndex(int index) {
        Node p = head;
        int pos = 0;
        while (p != null && pos != index) {
            p = p.next;
            ++pos;
        }
        return p;
    }


    public void insertToHead(int value) {
        Node newNode = new Node(value, null);

        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertToTail(int value) {
        Node newNode = new Node(value, null);

        if (head == null) {
            head = newNode;
        } else {
            Node p = head;
            while (p.next != null) {
                p = p.next;
            }
            newNode.next = p.next;
            p.next = newNode;
        }
    }


    public void insertAfter(Node p, int value) {
        Node newNode = new Node(value, null);
        if (p == null) {
            return;
        }
        newNode.next = p.next;
        p.next = newNode;
    }

    public void insertBefore(Node p, int value) {
        Node newNode = new Node(value, null);
        if (p == null) {
            return;
        }

        if (p == head) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node q = head;

        while (q != null && q.next != p) {
            q = q.next;
        }

        if (q == null) {
            return;
        }
        newNode.next = q.next;
        q.next = newNode;

    }

    public void deleteByNode(Node p) {
        if (p == null || head == null) {
            return;
        }

        if (head == p) {
            head = head.next;
            return;
        }

        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }

        if (q == null) {
            return;
        }

        q.next = q.next.next;


    }

    public void deleteByValue(int value) {
        if (head == null) {
            return;
        }

        Node p = head;
        //记录前一个节点位置
        Node q = null;

        while (p != null && p.data != value) {
            q = p;
            p = p.next;
        }
        if (p == null) {
            return;
        }

        if (q == null) {
            head = head.next;
        } else {
            q.next = q.next.next;
        }
    }


    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.println(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }


    /**
     * 判断是否为回文
     *
     * @return
     */
    public boolean palidrome() {

        return false;
    }


    /**
     * 带头节点的链表翻转
     *
     * @param p
     * @return
     */
    public Node inverseLinkList_head(Node p) {

        return p;
    }


    /**
     * 无头结点的链表翻转
     *
     * @param p
     * @return
     */
    public Node inverseLinkList(Node p) {

        return p;
    }


    public static Node createNode(int value) {
        return new Node(value, null);
    }


    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

    }

    public static void main(String[] args) {

    }


}
