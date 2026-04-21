package com.map;

import com.collection.ValueBasedCollection;
import com.list.CustomLinkedList;

public class MapLinkedList<K extends Comparable<K>, V> extends AbstractBucket<K, V> implements ValueBasedCollection<V> {

    private MapNode<K, V> first;
    private MapNode<K, V> last;

    private final CustomLinkedList<V> linkedList = new CustomLinkedList<>();

    @SafeVarargs
    public static <K extends Comparable<K>, V> MapLinkedList<K, V> of(K key, V... values) {
        MapLinkedList<K, V> mapLinkedList = new MapLinkedList<>();
        if (values != null) {
            for (V value : values) {
                mapLinkedList.add(value);
            }
        }
        return mapLinkedList;
    }

    @Override
    public V getValue(V value) {
        return linkedList.getValue(value);
    }

    @Override
    public V setValue(V oldValue, V newValue) {
        return linkedList.setValue(oldValue, newValue);
    }

    @Override
    public V removeValue(V value) {
        return linkedList.removeValue(value);
    }

    @Override
    public boolean contains(V value) {
        return linkedList.contains(value);
    }

    @Override
    public boolean add(V value) {
        return linkedList.add(value);
    }

    public V putNode(K key, V value, int hash) {
        MapNode<K, V> newNode = new MapNode<>(key, value, hash);
        if (first == null) {
            first = newNode;
            return value;
        }
        MapNode<K, V> prev = null;
        MapNode<K, V> node = first;
        K k;
        while (node != null) {
            if (node.hash == hash && ((k = node.key) == key || key != null && key.equals(k))) {
                node.value = value;
                return value;
            }
            prev = node;
            node = node.next;
        }
        prev.next = newNode;
        return value;
    }

    public MapNode<K, V> removeNode(K key, V value, int hash) {
        if (first == null) {
            return null;
        }
        MapNode<K, V> prev = null;
        MapNode<K, V> node = first;
        K k;
        while (node != null) {
            if (node.hash == hash && ((k = node.key) == key || key != null && key.equals(k))) {
                break;
            }
            prev = node;
            node = node.next;
        }

        V v;
        // primitives are autoboxed to reference
        // v == value - equal primitives  - false
        // v != value - unequal primitives or references - true
        if (node == null || ((v = node.value) != value && (value == null || !value.equals(v)))) {
            return null;
        }
        if (prev == null) {
            first = first.next;
        } else {
            prev.next = node.next;
        }
        node.next = null;
        return node;
    }

    public MapNode<K, V> getNode(K key, int hash) {
        return null;
    }
}
