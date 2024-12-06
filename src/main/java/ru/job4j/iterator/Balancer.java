package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {

    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> iterator) {
           int nodeCount = nodes.size();
           int indexNode = 0;
           while (iterator.hasNext()) {
               if (indexNode == nodeCount) {
                   indexNode = 0;
               }
               nodes.get(indexNode++).add(iterator.next());
           }
    }
}
