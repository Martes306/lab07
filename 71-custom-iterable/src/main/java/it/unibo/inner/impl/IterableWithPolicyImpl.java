package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private List<T> elements;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(final T[] elements) {
        this(elements, new Predicate<>() {
            @Override
            public boolean test(T elem) {
                return true;
            }
        });
    }

    public IterableWithPolicyImpl(final T[] array, final Predicate<T> filter) {
        this.elements = List.of(array);
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    @Override
    public void setIterationPolicy(final Predicate<T> filter) {
        this.filter = filter;
    }

    private class IteratorImpl implements Iterator<T> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            boolean result;
            while (result = (this.index < IterableWithPolicyImpl.this.elements.size() &&
                    !IterableWithPolicyImpl.this.filter.test(IterableWithPolicyImpl.this.elements.get(index)))) {
                index++;
            }
            return !result && this.index < IterableWithPolicyImpl.this.elements.size();
        }

        @Override
        public T next() {
            if (hasNext()) {
                return IterableWithPolicyImpl.this.elements.get(index++);
            }
            throw new NoSuchElementException();
        }
    }
}
