package com.collection;

public interface ValueBasedCollection<V> extends AbstractCollection<V> {
    V getValue(V value);

    V setValue(V oldValue, V newValue);

    V removeValue(V value);
}