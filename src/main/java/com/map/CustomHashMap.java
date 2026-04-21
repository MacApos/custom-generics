package com.map;

import java.util.HashMap;

public class CustomHashMap<K extends Comparable<K>, V> implements AbstractMap<K, V> {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75F;
    static final int TREEIFY_THRESHOLD = 8;
    static final int UNTREEIFY_THRESHOLD = 6;

    AbstractBucket[] table;
    private int size;
    private int capacity;
    private final float loadFactor;

    public CustomHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public CustomHashMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public CustomHashMap(float loadFactor) {
        this(DEFAULT_INITIAL_CAPACITY, loadFactor);
    }

    public CustomHashMap(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        table = new AbstractBucket[capacity];
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
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
        AbstractBucket<K,V> bucket;
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        if ((bucket = table[hash]) == null) {
            table[hash] = MapLinkedList.of(key, value);
        } else {
            if (bucket instanceof RedBlackTree<K, V> tree) {

            } else {

            }
        }
        return value;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V replace(K key, V oldValue, V newValue) {
        return null;
    }

}
