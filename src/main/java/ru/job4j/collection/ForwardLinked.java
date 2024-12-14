package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
       Node<T> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < size - 1; i++) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        modCount++;
    }
    
    public T get(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) {
            return head.item;
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException(
                    "No available element in collection."
            );
        }
        Node<T> secondNode =  head.next;
        T item = head.item;
        head.item = null;
        head.next = null;
        head = secondNode;
        size--;
        modCount--;
        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private int expectedModCount = modCount;

            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException(
                            "Collection was modify during iteration."
                    );
                }
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException(
                            "No available element in collection."
                    );
                }
                T item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    private static class Node<T> {

        private T item;

        private Node<T> next;

        Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }
}