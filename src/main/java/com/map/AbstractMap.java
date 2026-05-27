package com.map;

public interface AbstractMap<K, V> {
    int size();

    V get(K key);

    V put(K key, V value);

    V remove(K key);

    V replace(K key, V oldValue, V newValue);

    int hash(K key);
}
