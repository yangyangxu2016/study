package com.example.map;


import java.util.HashMap;
import java.util.LinkedHashMap;

public class XHashMap<K, V> {

    /**
     * node数组
     */
    transient Node<K, V>[] table;


    public V get(Object key) {
        Node<K, V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }


    private Node<K, V> getNode(int hash, Object key) {
        Node<K, V>[] tab;
        Node<K, V> first;
        Node<K, V> e;
        int n;
        K k;
        //桶数组不为空，桶大小不为空，
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (first = tab[(n - 1) & hash]) != null) {
            //判断第一个是否相等
            if (first.hash == hash &&
                    ((k = first.key) == key || (key != null && key.equals(k)))) {
                return first;
            }
            //循环链表
            if ((e = first) != null) {
                //判断是树还是链表
//                if (first instanceof TreeNode) {
//                    return null;
//                }
                do {
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                        return e;
                    }
                } while ((e = e.next) != null);
            }


        }
        return null;
    }


    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node next;

        public Node(int hash, K key, V value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


    static final class TreeNode<K, V> {
    }

}


