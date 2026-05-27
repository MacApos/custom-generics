package com.map;

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
        AbstractBucket<K, V> bucket;
        if ((bucket = table[hash]) == null) {
            bucket = new MapLinkedList<>();
            bucket.putNode(key, value, hash);
            table[hash] = bucket;
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

    }

    @Override
    public V remove(K key) {
        return null;
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
