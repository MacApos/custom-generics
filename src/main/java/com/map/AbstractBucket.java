package com.map;

public abstract class AbstractBucket<K extends Comparable<K>, V> {

    abstract public V putNode(K key, V value, int hash);

    abstract public V removeNode(K key, int hash);

    abstract int size();

}
