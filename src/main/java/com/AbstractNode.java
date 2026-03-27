package com;

import lombok.Setter;

public abstract class AbstractNode<V> {
    public V value;
    @Setter
    public AbstractNode<V> next;

    public AbstractNode(V value) {
        this.value = value;
    }

    public abstract AbstractNode<V> getNext();

}
