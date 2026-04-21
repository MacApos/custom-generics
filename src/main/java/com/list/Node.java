package com.list;


public class Node<V> extends AbstractNode<V>{
    public Node<V> next;

    public Node(V value) {
        super(value);
    }
}
