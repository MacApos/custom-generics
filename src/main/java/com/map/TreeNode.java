package com.map;


public class TreeNode<K, V> extends MapNode<K,V> {
    public boolean red = true;
    public TreeNode<K, V> parent;
    public TreeNode<K, V>[] link;

    public TreeNode(K key, V value, int hash) {
        super(key, value, hash);
        link = new TreeNode[2];
    }

    public TreeNode(K key, V value, int hash, boolean red) {
        this(key, value, hash);
        this.red = red;
    }
}
