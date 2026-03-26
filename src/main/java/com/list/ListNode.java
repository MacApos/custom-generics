package com.list;

import com.AbstractNode;

public class ListNode<T> extends AbstractNode<T> {
    ListNode<T> next;


    public ListNode(T value) {
        super(value);
    }
}
