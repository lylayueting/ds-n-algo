package com.github.lyt.dsnalgo.problems;

import org.junit.Test;

/**
 * Created by Yueting on 8/17/2017.
 */
public class InversePairsTest {

  @Test
  public void test() {
    int[] array = new int[200000];
    for (int i = 0; i < array.length; i++) {
      array[i] = 200000 - i;
    }

    System.out.println(InversePairs.inversePairs(array));
  }
}
