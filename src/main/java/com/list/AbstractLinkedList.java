package com.list;

public interface AbstractLinkedList<V> {
    boolean contains(V value);

    boolean add(V value);

    V get(int index);

    V remove(int index);

    V remove(V value);

    V set(int index, V newValue);

    V set(V oldValue, V newValue);
}