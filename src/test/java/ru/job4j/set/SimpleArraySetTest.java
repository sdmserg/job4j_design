package ru.job4j.set;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleArraySetTest {

    @Test
    void whenAddNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddSomeElementsNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.contains(1)).isFalse();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
        assertThat(set.contains(2)).isFalse();
        assertThat(set.add(2)).isTrue();
        assertThat(set.contains(2)).isTrue();
        assertThat(set.add(2)).isFalse();
        assertThat(set.contains(3)).isFalse();
        assertThat(set.add(3)).isTrue();
        assertThat(set.contains(3)).isTrue();
        assertThat(set.add(3)).isFalse();
    }

    @Test
    void whenAddNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenSetIsEmpty() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.contains(10)).isFalse();
        assertThat(set.iterator().hasNext()).isFalse();
    }

    @Test
    void whenAddStringsAndNull() {
        SimpleArraySet<String> set = new SimpleArraySet<>();
        set.add("first");
        set.add("second");
        set.add(null);
        set.add("first");
        set.add(null);
        set.add("five");
        set.add("seven");
        assertThat(set).hasSize(5);
        assertThat(set.contains("first")).isTrue();
        assertThat(set.contains("second")).isTrue();
        assertThat(set.contains("five")).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.contains("seven")).isTrue();
        assertThat(set.contains("ten")).isFalse();
        assertThat(set.contains("First")).isFalse();
        assertThat(set.contains("SECOND")).isFalse();
    }

    @Test
    void whenSimpleCheckIterator() {
        SimpleArraySet<Integer> set = new SimpleArraySet<>();
        set.add(5);
        set.add(7);
        set.add(10);
        Iterator<Integer> iteratorSet = set.iterator();
        assertThat(iteratorSet.hasNext()).isTrue();
        assertThat(iteratorSet.next()).isEqualTo(5);
        assertThat(iteratorSet.hasNext()).isTrue();
        assertThat(iteratorSet.next()).isEqualTo(7);
        assertThat(iteratorSet.hasNext()).isTrue();
        assertThat(iteratorSet.next()).isEqualTo(10);
        assertThat(iteratorSet.hasNext()).isFalse();
        assertThatThrownBy(iteratorSet::next)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("No available element in collection.");
    }

    @Test
    void whenAddElementDuringIterationThenExceptionThrown() {
        SimpleArraySet<Integer> set = new SimpleArraySet<>();
        set.add(5);
        set.add(7);
        set.add(10);
        Iterator<Integer> iteratorSet = set.iterator();
        assertThat(iteratorSet.hasNext()).isTrue();
        assertThat(iteratorSet.next()).isEqualTo(5);
        set.add(100);
        assertThatThrownBy(iteratorSet::next)
                .isInstanceOf(ConcurrentModificationException.class)
                .hasMessage("Collection was modify during iteration.");
    }
}