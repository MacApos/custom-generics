package com.map;

public interface AbstractBucket<K extends Comparable<K>, V> {
    int size();

    V getNode(K key, int hash);

    V putNode(K key, V value, int hash);

    V removeNode(K key, int hash);

}
