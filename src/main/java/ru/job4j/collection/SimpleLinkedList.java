package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E>  implements SimpleLinked<E> {

    private int size;

    private int modCount;

    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = head;
            for (int i = 0; i < size - 1; i++) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> current = head;
        for (int i = 0; i != index; i++) {
            current = current.next;
        }
        return current.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int expectedModCount = modCount;

            private Node<E> current = head;

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
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException(
                            "No available element in collection."
                    );
                }
                E item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    private static class Node<E> {

        private E item;

        private Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}
