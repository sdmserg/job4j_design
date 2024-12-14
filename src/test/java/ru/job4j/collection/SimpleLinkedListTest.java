package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SimpleLinkedListTest {

    private SimpleLinked<Integer> list;

    @BeforeEach
    public void initData() {
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
    }

    @Test
    void checkIteratorSimple() {
        assertThat(list).hasSize(2);
        list.add(3);
        list.add(4);
        assertThat(list).hasSize(4);
    }

    @Test
    void whenAddAndGet() {
        list.add(3);
        list.add(4);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(3);
        assertThat(list.get(3)).isEqualTo(4);
    }

    @Test
    void whenGetFromOutOfBoundThenExceptionThrown() {
        assertThatThrownBy(() -> list.get(2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddIterHasNextTrue() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenAddIterNextOne() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo(1);
    }

    @Test
    void whenEmptyIterHashNextFalse() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void whenAddIterMultiHasNextTrue() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenAddIterNextOneNextTwo() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
    }

    @Test
    void whenGetIteratorTwiceThenEveryFromBegin() {
        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext()).isTrue();
        assertThat(first.next()).isEqualTo(1);
        assertThat(first.hasNext()).isTrue();
        assertThat(first.next()).isEqualTo(2);
        assertThat(first.hasNext()).isFalse();
        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext()).isTrue();
        assertThat(second.next()).isEqualTo(1);
        assertThat(second.hasNext()).isTrue();
        assertThat(second.next()).isEqualTo(2);
        assertThat(second.hasNext()).isFalse();
    }

    @Test
    void whenSimpleLIstIsEmptyThenNextIsException() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> iterator = list.iterator();
        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenModifySimpleListDuringIterationThenException() {
        Iterator<Integer> iterator = list.iterator();
        list.add(10);
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class)
                .hasMessage("Collection was modify during iteration.");
    }

    @Test
    void whenAddNullThenElementIsNullAndSizeIsCorrect() {
        list.add(null);
        assertThat(list.get(2)).isNull();
        assertThat(list).hasSize(3);
    }

    @Test
    void whenAddNullHasNextTrueAndNextNull() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(null);
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isNull();
    }
}
