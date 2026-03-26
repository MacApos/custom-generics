package com.map;

public class TreeNode<K extends Comparable<K>, V> extends MapNode<K, V> {
    TreeNode<K, V> parent;
    TreeNode<K, V> left;
    TreeNode<K, V> right;
    boolean red;

    public TreeNode(K key, V value) {
        super(key, value);
    }
}
