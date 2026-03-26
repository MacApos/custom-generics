package com.list;

public class CustomList<V> {
    int size;
    ListNode<V> first;
    ListNode<V> last;

    private void checkIndex(int index) {
        if (index <= 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
    }

    public void add(V value) {
        ListNode<V> node = new ListNode<>(value);
        if (first == null) {
            first = node;
        } else {
            last.next = node;
            last = node;
        }
        size++;
    }

    public V get(int index) {
        return getNode(index).value;
    }

    private ListNode<V> getNode(int index) {
        checkIndex(index);
        ListNode<V> node = first;
        int i = 0;
        while (i++ != index) {
            node = node.next;
        }
        return node;
    }

    public void replace(int index, V newValue) {
        getNode(index).value = newValue;
    }


    public V remove(int index) {
        checkIndex(index);
        ListNode<V> prev = getNode(Math.min(0, index - 1));
        ListNode<V> node;
        if(true){

        }

        return null;
    }
}
