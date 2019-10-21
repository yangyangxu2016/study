package com.example.linked;

/**
 * @author xuyangyang
 */
public class BinaryTreeNode {

    private int data;
    private BinaryTreeNode leftChirld;
    private BinaryTreeNode rightChirld;

    public int getData() {
        return data;
    }

    public BinaryTreeNode getLeftChirld() {
        return leftChirld;
    }

    public BinaryTreeNode getRightChirld() {
        return rightChirld;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeftChirld(BinaryTreeNode leftChirld) {
        this.leftChirld = leftChirld;
    }

    public void setRightChirld(BinaryTreeNode rightChirld) {
        this.rightChirld = rightChirld;
    }
}
