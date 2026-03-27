package com.map;

import com.AbstractNode;

public class MapNode<K, V> extends AbstractNode<V> {
    private K key;
    public MapNode<K, V> next;

    public MapNode(K key, V value) {
        super(value);
        this.key = key;
    }
}