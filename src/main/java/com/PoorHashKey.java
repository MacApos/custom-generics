package com;

import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class PoorHashKey<K> {
    private final K key;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PoorHashKey<?> that)) return false;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
