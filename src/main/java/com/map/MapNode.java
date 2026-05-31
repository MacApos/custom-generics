package com.map;

import com.list.AbstractNode;

public class MapNode<K, V> extends AbstractNode<V> {
    public K key;
    public int hash;
    public MapNode<K, V> next;

    public MapNode(K key, V value, int hash) {
        super(value);
        this.hash = hash;
        this.key = key;
    }
}
