package com.list;

import lombok.Getter;

import java.util.function.Supplier;

public class CustomLinkedList<V> implements AbstractLinkedList<V> {
    @Getter
    private Node<V> first;
    @Getter
    private Node<V> last;
    private int size;

    @SafeVarargs
    public static <V> CustomLinkedList<V> of(V... values) {
        CustomLinkedList<V> customLinkedList = new CustomLinkedList<>();
        if (values != null) {
            for (V value : values) {
                customLinkedList.add(value);
            }
        }
        return customLinkedList;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
    }

    private void checkValue(V value) {
        if (value == null) {
            throw new IllegalArgumentException("Value is null");
        }
    }

    private Node<V> getNodeByIndex(int index) {
        Node<V> node = first;
        int i = 0;
        while (i++ != index) {
            node = node.next;
        }
        return node;
    }

    private Node<V> getNodeByValue(V value) {
        Node<V> node = first;
        while (node != null) {
            if (node.value.equals(value)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    private Node<V> getPrevNodeByValue(V value) {
        Node<V> node = first;
        while (node.next != null) {
            if (node.next.value.equals(value)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    private V setNode(Node<V> node, V newValue) {
        if (node == null) {
            return null;
        }
        node.value = newValue;
        return node.value;
    }

    private V removeNode(boolean isFirst, Supplier<Node<V>> supplier) {
        Node<V> prev = null;
        Node<V> node = first;
        if (isFirst) {
            first = first.next;
        } else {
            prev = supplier.get();
            if (prev == null) {
                return null;
            }
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
        return node.value;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean contains(V value) {
        return getNodeByValue(value) == null;
    }

    @Override
    public boolean add(V value) {
        Node<V> newNode = new Node<>(value);
        if (first == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
        return true;
    }

    @Override
    public V get(int index) {
        checkIndex(index);
        return getNodeByIndex(index).value;
    }

    @Override
    public V remove(int index) {
        checkIndex(index);
        return removeNode(index == 0, () -> getNodeByIndex(index - 1));
    }

    @Override
    public V remove(V value) {
        checkValue(value);
        return removeNode(first.value.equals(value), () -> getPrevNodeByValue(value));
    }

    @Override
    public V set(int index, V newValue) {
        checkIndex(index);
        checkValue(newValue);
        return setNode(getNodeByIndex(index), newValue);
    }

    @Override
    public V set(V oldValue, V newValue) {
        checkValue(oldValue);
        checkValue(newValue);
        return setNode(getNodeByValue(oldValue), newValue);
    }
}
