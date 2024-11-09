package it.unibo.inner.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private List<T> elements;

    public IterableWithPolicyImpl(final T[] array) {
        this.elements = List.of(array);
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIterationPolicy'");
    }

    private class IteratorImpl implements Iterator<T> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            if (this.index < IterableWithPolicyImpl.this.elements.size()) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public T next() {
            return IterableWithPolicyImpl.this.elements.get(index++);
        }

    }
}
