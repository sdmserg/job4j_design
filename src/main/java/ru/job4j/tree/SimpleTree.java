package ru.job4j.tree;

import java.util.Optional;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {

   private Node<E> root;

   public SimpleTree(final E root) {
       this.root = new Node<>(root);
   }

    @Override
    public boolean add(E parent, E children) {
       Optional<Node<E>> parentNode = findBy(parent);
       Optional<Node<E>> node = findBy(children);
       boolean result = parentNode.isPresent() && node.isEmpty();
       if (result) {
           parentNode.get().children.add(new Node<>(children));
       }
       return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
       return findByPredicate(node -> node.value.equals(value));
    }

    public boolean isBinary() {
       Optional<Node<E>> result = findByPredicate(node -> node.children.size() > 2);
       return result.isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
       Optional<Node<E>> result = Optional.empty();
       Queue<Node<E>> data = new LinkedList<>();
       data.offer(root);
       while (!data.isEmpty()) {
           Node<E> element = data.poll();
           if (condition.test(element)) {
               result = Optional.of(element);
               break;
           }
           data.addAll(element.children);
       }
       return result;
    }
}
