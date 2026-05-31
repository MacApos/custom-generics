package com.map;

import java.util.ArrayDeque;

public class CustomHashMap<K extends Comparable<K>, V> implements AbstractMap<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private final int TREEIFY_THRESHOLD = 8;
    private final int UNTREEIFY_THRESHOLD = 6;
    private final AbstractBucket[] table;
    private final int capacity;
    private int size;

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
        int hash = hash(key);
        int idx = table.length - 1 & hash;
        AbstractBucket<K, V> bucket;
        if ((bucket = table[idx]) == null) {
            return null;
        }
        return bucket.getNode(key, hash);
    }

    @Override
    public V put(K key, V value) {
        int hash = hash(key);
        int idx = table.length - 1 & hash;
        AbstractBucket<K, V> bucket;
        if ((bucket = table[idx]) == null) {
            bucket = new MapLinkedList<>();
            table[idx] = bucket;
        } else {
            if (bucket instanceof MapLinkedList<K, V> linkedList && linkedList.size() >= TREEIFY_THRESHOLD) {
                treeify(linkedList, hash, idx);
                table[idx] = bucket;
            }
        }
        bucket.putNode(key, value, hash);

        return value;
    }


    @Override
    public V remove(K key) {
        int hash = hash(key);
        int idx = table.length - 1 & hash;
        AbstractBucket<K, V> bucket;
        if ((bucket = table[idx]) == null) {
            return null;
        }
        V value = bucket.removeNode(key, hash);

        if (bucket instanceof RedBlackTree<K, V> tree && tree.size() < UNTREEIFY_THRESHOLD) {
            untreeify(tree, hash, idx);
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

    public void treeify(MapLinkedList<K, V> linkedList, int hash, int idx) {
        MapNode<K, V> node = linkedList.getFirst();
        RedBlackTree<K, V> tree = new RedBlackTree<>();
        while (node != null) {
            tree.putNode(node.key, node.value, node.hash);
            node = node.next;
        }
        table[idx] = tree;
    }

    public void untreeify(RedBlackTree<K, V> tree, int hash, int idx) {
        MapLinkedList<K, V> linkedList = new MapLinkedList<>();
        ArrayDeque<TreeNode<K, V>> deque = new ArrayDeque<>();

        TreeNode<K, V> root = tree.root;
        deque.add(root);

        while (!deque.isEmpty()) {
            TreeNode<K, V> poll = deque.poll();
            linkedList.putNode(poll.key, poll.value, poll.hash);
            TreeNode<K, V> left;
            if ((left = poll.link[0]) != null) {
                linkedList.putNode(left.key, left.value, left.hash);
            }
            TreeNode<K, V> right;
            if ((right = poll.link[0]) != null) {
                linkedList.putNode(right.key, right.value, right.hash);
            }
        }
        table[idx] = linkedList;
    }

}
