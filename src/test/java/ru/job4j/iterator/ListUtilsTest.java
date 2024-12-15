package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterLastElement() {
        ListUtils.addAfter(input, 1, 5);
        assertThat(input).containsExactly(1, 3, 5);
    }

    @Test
    void whenAddAfterInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 3, 5))
                .isInstanceOf(IndexOutOfBoundsException.class);

    }

    @Test
    void whenRemoveIfGreaterThen4() {
        input = new ArrayList<>(Arrays.asList(1, 5, 8, 3, 0, 9, 2, 1, 5));
        ListUtils.removeIf(input, (value) -> value > 4);
        assertThat(input).containsExactly(1, 3, 0, 2, 1);
    }

    @Test
    void whenRemoveIfGreaterThen10() {
        input = new ArrayList<>(Arrays.asList(1, 5, 8, 3, 0, 9, 2, 1, 5));
        ListUtils.removeIf(input, (value) -> value > 10);
        assertThat(input).containsExactly(1, 5, 8, 3, 0, 9, 2, 1, 5);
    }

    @Test
    void replaceIfEquals5() {
        input = new ArrayList<>(Arrays.asList(1, 5, 8, 3, 0, 9, 2, 1, 5));
        ListUtils.replaceIf(input, (value) -> value == 5, 7);
        assertThat(input).containsExactly(1, 7, 8, 3, 0, 9, 2, 1, 7);
    }

    @Test
    void replaceIfEqualsNull() {
        input = new ArrayList<>(Arrays.asList(1, 5, 8, 3, 0, 9, 2, 1, 5));
        ListUtils.replaceIf(input, (value) -> value == null, 9);
        assertThat(input).containsExactly(1, 5, 8, 3, 0, 9, 2, 1, 5);
    }

    @Test
    void removeAll() {
        input = new ArrayList<>(Arrays.asList(1, 5, 8, 3, 0, 9, 2, 1, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 5, 9));
        ListUtils.removeAll(input, elements);
        assertThat(input).containsExactly(8, 3, 0, 2);
    }

    @Test
    void removeAllWithEmptyList() {
        input = new ArrayList<>(Arrays.asList(1, 5, 8, 3, 0, 9, 2, 1, 5));
        List<Integer> elements = new ArrayList<>();
        ListUtils.removeAll(input, elements);
        assertThat(input).containsExactly(1, 5, 8, 3, 0, 9, 2, 1, 5);
    }
}