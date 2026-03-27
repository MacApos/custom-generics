package com.map;

import lombok.Data;

@Data
public class CustomMap<K extends Comparable<K>, V> {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75F;
    static final int TREEIFY_THRESHOLD = 8;
    static final int UNTREEIFY_THRESHOLD = 6;

    MapNode<K, V>[] table;
    private int size;
    private int capacity;
    private final float loadFactor;

    public CustomMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public CustomMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public CustomMap(float loadFactor) {
        this(DEFAULT_INITIAL_CAPACITY, loadFactor);
    }

    public CustomMap(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        table = new MapNode[capacity];
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public void put(K key, V value) {
        int hash = hash(key);
        MapNode<K, V> mapNode;
        if ((mapNode = table[hash]) == null) {
            table[hash] = new MapNode<>(key, value);
        } else {
            if (mapNode instanceof TreeNode<K, V> treeNode) {

            } else {
                MapNode<K, V> next;
                do {
                    next = mapNode.next;
                } while (next != null);
            }
        }
    }

    public void get() {
    }

    public void replace() {
    }

    public void remove() {
    }

}
