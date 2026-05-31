package com.map;

public interface AbstractBucket<K extends Comparable<K>, V> {

    V putNode(K key, V value, int hash);

    V removeNode(K key, int hash);

    V getNode(K key, int hash);

    int size();

}
