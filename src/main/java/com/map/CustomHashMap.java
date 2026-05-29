package com.map;

import java.util.ArrayDeque;

public class CustomHashMap<K extends Comparable<K>, V> implements AbstractMap<K, V> {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final int TREEIFY_THRESHOLD = 8;
    static final int UNTREEIFY_THRESHOLD = 6;

    private int size;
    private final AbstractBucket[] table;
    private final int capacity;

    public CustomHashMap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public CustomHashMap(int capacity) {
        this.capacity = capacity;
        table = new AbstractBucket[capacity];
    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        int hash = hash(key);
        int idx = table.length - 1 & hash;
        AbstractBucket<K, V> bucket;
        if ((bucket = table[idx]) == null) {
            bucket = new MapLinkedList<>();
            bucket.putNode(key, value, hash);
            table[idx] = bucket;
        } else {
            bucket.putNode(key, value, hash);
            if (bucket instanceof MapLinkedList<K, V> linkedList && linkedList.size() >= TREEIFY_THRESHOLD) {
                treeify(linkedList, hash);
            }
        }
        return value;
    }

    public void treeify(MapLinkedList<K, V> linkedList, int hash) {
        MapNode<K, V> node = linkedList.getFirst();
        RedBlackTree<K, V> tree = new RedBlackTree<>();
        while (node != null) {
            tree.putNode(node.key, node.value, node.hash);
            node = node.next;
        }
        table[hash] = tree;
    }

    public void untreeify(RedBlackTree<K, V> tree, int hash) {
        MapLinkedList<K, V> linkedList = new MapLinkedList<>();
        ArrayDeque<TreeNode<K, V>> deque = new ArrayDeque<>();

        TreeNode<K, V> root = tree.root;
        deque.add(root);

        while (!deque.isEmpty()) {
            TreeNode<K, V> poll = deque.poll();
            linkedList.putNode(poll.key, poll.value, poll.hash);
            for (TreeNode<K, V> node : poll.link) {
                if (node != null) {
                    linkedList.putNode(node.key, node.value, node.hash);
                }
            }
        }
        table[hash] = linkedList;
    }

    @Override
    public V remove(K key) {
        int hash = hash(key);
        int idx = table.length - 1 & hash;
        AbstractBucket<K, V> bucket;
        if((bucket = table[idx])==null){
            return null;
        }
        V value = bucket.removeNode(key, hash);

        if (bucket instanceof RedBlackTree<K, V> tree && tree.size() < UNTREEIFY_THRESHOLD) {
            untreeify(tree, hash);
        }

        return value;
    }

    @Override
    public V replace(K key, V oldValue, V newValue) {
        return null;
    }

    @Override
    public int hash(K key) {
        int i = (key.hashCode() & 0x7fffffff) % capacity;
        return 1;
    }

}
