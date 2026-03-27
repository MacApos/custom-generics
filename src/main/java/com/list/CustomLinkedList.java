package com.list;

import com.AbstractNode;

public class CustomLinkedList<V> {
    private int size;
    private AbstractNode<V> first;
    private AbstractNode<V> last;

    public int size() {
        return size;
    }

    public V getFirst() {
        return first.value;
    }

    public V getLast() {
        return last.value;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
    }

    public boolean add(V value) {
        return addNode(new ListNode<>(value));
    }

    private boolean addNode(AbstractNode<V> newNode) {
        if (first == null) {
            first = newNode;
        } else {
            AbstractNode<V> node = first;
            do {
                if (newNode.value.equals(node.value)) {
                    return false;
                }
                node = node.next;
            } while (node != null);
            last.next = newNode;
        }
        last = newNode;
        size++;
        return true;
    }

    public V get(int index) {
        return getNode(index).value;
    }

    private AbstractNode<V> getNode(int index) {
        checkIndex(index);
        AbstractNode<V> node = first;
        int i = 0;
        while (i++ != index) {
            node = node.next;
        }
        return node;
    }

    public V set(int index, V newValue) {
        return setNode(index, newValue).value;
    }

    private AbstractNode<V> setNode(int index, V newValue) {
        checkIndex(index);
        AbstractNode<V> node = getNode(index);
        node.value = newValue;
        return node;
    }

    public V remove(int index) {
        return removeNode(index).value;
    }

    private AbstractNode<V> removeNode(int index) {
        checkIndex(index);
        AbstractNode<V> node;
        AbstractNode<V> prev = null;
        if (index == 0) {
            node = first;
            first = first.next;
        } else {
            prev = getNode(index - 1);
            node = prev.next;
            prev.next = node.next;
        }
        if (first == null) {
            last = null;
        } else if (node.next == null) {
            last = prev;
        }
        node.next = null;
        size--;
        return node;
    }
}
