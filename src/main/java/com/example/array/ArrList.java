package com.example.array;

import java.util.Arrays;

/**
 * 实现一个支持动态扩容的数组
 *
 * @author xuyangyang
 */
public class ArrList implements IArray {


    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final Object[] EMPTY_ELEMENTDATA = {};

    private int size = 0;
    transient Object[] elementData;


    /**
     * 插入元素如果在数组尾部插入，复杂度是O(1)
     *
     * @param value
     * @return
     */
    @Override
    public boolean add(Integer value) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = value;
        return true;
    }

    /**
     * 插入指定位置，最好渐进时间复杂度是O(1)，最坏是O(n),
     * 平均是1+2+3+...+n/n=O(n)
     *
     * @param index
     * @param value
     */
    @Override
    public void add(int index, Object value) {
        rangeCheckForAdd(index);
        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }


    @Override
    public Object remove(Integer index) {
        rangeCheck(index);
        Object oldValue = elementData[index];
        int moveNum = size - index - 1;
        if (moveNum > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, moveNum);
        }

        elementData[--size] = null;
        return oldValue;
    }

    private void rangeCheck(Integer index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("数组下表越界");
        }
    }

    @Override
    public boolean remove(Object value) {
        if (value == null) {
            for (int index = 0; index < size; index++) {
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (elementData[index].equals(value)) {
                    fastRemove(index);
                    return true;
                }

            }
        }
        return false;
    }

    private void fastRemove(int index) {
        int numsMove = size - index - 1;
        if (numsMove > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numsMove);
        }
        elementData[--size] = null;
    }

    @Override
    public int size() {
        return size;
    }


    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));

    }

    private void ensureExplicitCapacity(int minCapacity) {
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }

    }

    private void grow(int minCapacity) {

        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    private void rangeCheckForAdd(Integer index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("数组下表越界异常");
        }
    }
}
