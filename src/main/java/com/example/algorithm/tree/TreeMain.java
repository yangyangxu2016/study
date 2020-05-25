package com.example.algorithm.tree;

public class TreeMain {
    public static void main(String[] args) {
        TreeMain tree = new TreeMain();
        tree.insert(0);

    }

    private void insert(int i) {


    }


    public void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.getData());
        preOrder(node.left);
        preOrder(node.right);

    }


    public static class Node {
        private int data;
        private Node right;
        private Node left;

        public Node() {
        }

        public Node(int data) {
            this.data = data;
        }

        public Node(int root, Node right, Node left) {
            this.data = root;
            this.right = right;
            this.left = left;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }


    }
}
