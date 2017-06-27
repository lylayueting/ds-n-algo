package com.github.lyt.dsnalgo.problems;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * Created by Yueting on 6/26/2017.
 */
public class StringFrequencySort {

  public static String frequencySort(String s) {

    if (s == null || s.length() == 0) {
      return s;
    }

    Map<Character, Integer> map = new HashMap<>();

    for (char c : s.toCharArray()) {
      if (map.containsKey(c)) {
        map.put(c, map.get(c) + 1);
      } else {
        map.put(c, 1);
      }
    }

//    Comparator<Entry<Character, Integer>> ascComparator = Comparator.comparingInt(Entry::getValue);
    Comparator<Entry<Character, Integer>> descComparator = (left, right) ->
        right.getValue() - left.getValue();

    PriorityQueue<Entry<Character, Integer>> priorityQueue = new PriorityQueue<>(descComparator);

    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
      priorityQueue.offer(entry);
    }

    StringBuilder builder = new StringBuilder();

    while (!priorityQueue.isEmpty()) {
      Map.Entry<Character, Integer> top = priorityQueue.poll();
      for (int i = 0; i < top.getValue(); i++) {
        builder.append(top.getKey());
      }
    }

    return builder.toString();
  }
}
