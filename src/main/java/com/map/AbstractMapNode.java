package com.map;

import com.list.AbstractNode;

public abstract class AbstractMapNode<K,V> extends AbstractNode<V> {
    public K key;
    public int hash;

    public AbstractMapNode(K key, V value, int hash) {
        super(value);
        this.hash = hash;
        this.key = key;
    }
}
