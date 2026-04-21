package com.collection;

public interface AbstractCollection<V> {
    boolean contains(V value);

    boolean add(V value);
}