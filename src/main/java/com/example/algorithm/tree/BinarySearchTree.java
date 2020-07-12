package com.example.algorithm.tree;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 二叉搜索树
 */
@Slf4j
@Data
public class BinarySearchTree {

    private Node tree;

    public static void main(String[] args) {
        BinarySearchTree node = new BinarySearchTree();
        BinarySearchTree node1 = new BinarySearchTree();
        node.insert(1);
        node.insert(2);
        node.insert(3);
        node.insert(4);
        node.insert(5);
        System.out.println(node.find(1).toString());
        node.preorder(node.tree);
        System.out.println("================================");
        node.delete(1);
        node.preorder(node.tree);
        System.out.println("================================");
        node.delete(5);
        node.preorder(node.tree);
        System.out.println("================================");
        node.delete(3);
        node.preorder(node.tree);
        System.out.println("================================");


    }

    /**
     * 查找
     *
     * @param data
     * @return
     */
    public Node find(int data) {
        Node p = tree;
        while (p != null) {
            if (data < p.data) {
                p = p.left;
            } else if (data > p.data) {
                p = p.right;
            } else {
                return p;
            }

        }

        return null;
    }

    /**
     * 插入
     * 1、根节点为null，直接插入
     * 2、根节点不为了，判断是否小于左节点，
     * 3.是否大于右节点
     *
     * @param data
     */
    public void insert(int data) {
        if (tree == null) {
            tree = new Node(data);
            return;
        }

        Node p = tree;
        while (p != null) {
            if (data > p.data) {
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            } else {
                if (p.left == null) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    /**
     * 删除
     * 1.删除的节点，没有子节点，直接把父节点指针null
     * 2.删除的节点，只有一个子节点，更新父节点执行要删除的子节点的子节点
     * 3.删除的节点，有两个子节点，找到这个待删除的节点右子树的最小节点，替换到这个待删除节点上，删除右子树的最小节点
     *
     * @param data
     */
    public void delete(int data) {
        //指向要删除的节点，初始化指向根节点
        Node p = tree;
        //p的父节点
        Node pp = null;

        //寻找待删除的节点
        while (p != null && p.data != data) {
            pp = p;
            if (data > p.data) {
                p = p.right;
            } else {
                p = p.left;
            }
        }

        //没有找到
        if (p == null) {
            return;
        }

        //要删除的节点右两个子节点
        if (p.left != null && p.right != null) {
            //查找右子树的最小节点,一直往左子树查找
            Node minP = p.right;
            Node minPP =p;
            while (minP.left!= null) {
                minPP = minP;
                minP = minPP.left;
            }
            //将minP的数据替换到 p 中
            p.data = minP.data;
            //下面就变成了了删除的 minP 了
            p = minP;
            pp = minPP;
        }



        //要删除的节点有一个子节点或者是叶子节点

        Node child;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        //删除根节点

        if (pp ==null) {
            tree = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }

    }

    /**
     * 前序遍历
     *
     * @param node
     */
    public void preorder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        preorder(node.left);
        preorder(node.right);
    }

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node() {

        }

        public Node(int data) {
            this.data = data;
        }

        public Node(Node left, Node right, int data) {
            this.left = left;
            this.right = right;
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
