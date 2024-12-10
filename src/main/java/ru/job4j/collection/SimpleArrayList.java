package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = growArray();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removeValue = container[index];
        int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(
                    container,
                    index + 1,
                    container,
                    index,
                 newSize - index);
        }
        container[newSize] = null;
        size = newSize;
        modCount++;
        return removeValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private int indexCount = 0;

            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException(
                            "Collection was modify during iteration."
                    );
                }
                return indexCount < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException(
                            "No available element in collection."
                    );
                }
                return container[indexCount++];
            }
        };
    }

    private T[] growArray() {
        if (container.length == 0) {
            return (T[]) new Object[10];
        }
        return Arrays.copyOf(container, container.length * 2);
    }
}
