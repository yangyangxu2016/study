package com.example.linked;

/**
 * @author xuyangyang
 */
public class BinaryTree {

    private BinaryTreeNode root;

    public BinaryTree(BinaryTreeNode root) {
        this.root = root;
    }


    private BinaryTree() {

    }


    public BinaryTreeNode getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode root) {
        this.root = root;
    }


    public void clear() {
        this.clear(this.root);
    }

    private void clear(BinaryTreeNode node) {
        if (node != null) {
            clear(node.getLeftChirld());
            clear(node.getRightChirld());
            node = null;//删除节点
        }
    }

    public boolean isEmpty() {
        return root == null;
    }


    public void preOrder(BinaryTreeNode node) {
        if (node != null) {
            System.out.println(node.getData());
            preOrder(node.getLeftChirld());
            preOrder(node.getRightChirld());
        }
    }





}
