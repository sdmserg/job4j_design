package ru.job4j.tree;

import java.util.Optional;
import java.util.LinkedList;
import java.util.Queue;

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
       Optional<Node<E>> result = Optional.empty();
       Queue<Node<E>> data = new LinkedList<>();
       data.offer(root);
       while (!data.isEmpty()) {
           Node<E> element = data.poll();
           if (element.value.equals(value)) {
               result = Optional.of(element);
               break;
           }
           data.addAll(element.children);
       }
       return result;
    }
}
