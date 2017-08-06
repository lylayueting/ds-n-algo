package com.github.lyt.dsnalgo.problems;

import org.junit.Test;

/**
 * Created by Yueting on 8/5/2017.
 */
public class PathInMatrixTest {

  @Test
  public void test() {
    char[] matrix = "abcesfcsadee".toCharArray();
    char[] str = {'b', 'c', 'c', 'e', 'd'};

    boolean result = new PathInMatrix().hasPath(matrix, 3, 4, str);
    System.out.println(result);
  }
}
