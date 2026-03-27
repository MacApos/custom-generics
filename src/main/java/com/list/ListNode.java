package com.list;

import com.AbstractNode;

public class ListNode<T> extends AbstractNode<T> {


    public ListNode(T value) {
        super(value);
    }

    @Override
    public AbstractNode<T> getNext() {
        return (ListNode<T>) next;
    }


}
