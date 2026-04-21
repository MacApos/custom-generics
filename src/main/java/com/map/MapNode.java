package com.map;

public class MapNode<K, V> extends AbstractMapNode<K, V> {
    public MapNode<K, V> next;
    public MapNode(K key, V value, int hash) {
        super(key, value, hash);
    }
}
