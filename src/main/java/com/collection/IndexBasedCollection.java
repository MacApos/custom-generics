package com.collection;

public interface IndexBasedCollection<V> extends AbstractCollection<V> {
    V get(int index);

    V set(int index, V newValue);

    V remove(int index);
}
