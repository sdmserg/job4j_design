package ru.job4j.tree;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTreeTest {

    @Test
    void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6).isPresent());
    }

    @Test
    void whenElFindNotExistThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7).isEmpty());
    }

    @Test
    void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.add(2, 6)).isFalse();
    }

    @Test
    void whenParentElDoesNotExistThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.add(4, 5)).isFalse();
    }

    @Test
    void whehAdd3ChildrenThenChildrenSizeis3() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(2, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        assertThat(tree.findBy(2).get().children).hasSize(3);
    }

    @Test
    void when3ChildrenThenBinaryIsFalse() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(2, 6);
        assertThat(tree.isBinary()).isFalse();
    }

    @Test
    void whenTreeIsBinaryThenTrue() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(6, 7);
        tree.add(6, 8);
        tree.add(5, 9);
        tree.add(5, 11);
        assertThat(tree.isBinary()).isTrue();
    }

    @Test
    void when1ChildrenThenBinaryIsTrue() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.isBinary()).isTrue();
    }

    @Test
    void whenChildrenDoesNotExistThenBinaryIsTrue() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        assertThat(tree.isBinary()).isTrue();
    }
}
