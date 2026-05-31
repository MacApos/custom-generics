package com.map;

import lombok.Getter;

public class MapLinkedList<K extends Comparable<K>, V> implements AbstractBucket<K, V>  {

    @Getter
    private MapNode<K, V> first;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public V putNode(K key, V value, int hash) {
        MapNode<K, V> newNode = new MapNode<>(key, value, hash);
        if (first == null) {
            first = newNode;
            size = 1;
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
        size++;
        prev.next = newNode;
        return value;
    }

    @Override
    public V removeNode(K key, int hash) {
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
        if (node == null ) {
            return null;
        }
        size--;
        if (prev == null) {
            first = first.next;
        } else {
            prev.next = node.next;
        }
        node.next = null;
        return node.value;
    }


    public V getNode(K key, int hash) {
        MapNode<K, V> node = first;
        K k;
        while (node != null) {
            if (node.hash == hash && ((k = node.key) == key || key != null && key.equals(k))) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }
}
