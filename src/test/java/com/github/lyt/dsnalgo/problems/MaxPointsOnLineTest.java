package com.github.lyt.dsnalgo.problems;

import com.github.lyt.dsnalgo.problems.MaxPointsOnLine.Point;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Yueting on 6/28/2017.
 */
public class MaxPointsOnLineTest {

  @Test
  public void testMaxPoints() {
    Point a = new Point(0, 0);
    Point b = new Point(94911151, 94911150);
    Point c = new Point(94911152, 94911151);

    Point[] points = {a, b, c};

    Arrays.asList(points);

    Assert.assertEquals(2, MaxPointsOnLine.maxPoints(points));
  }

  @Test
  public void test() {
    char[] matrix = "abcesfcsadee".toCharArray();
    char[] str = {'b', 'c', 'c', 'e', 'd'};

    boolean result = new Problem().hasPath(matrix, 3, 4, str);
    System.out.println(result);
  }
}
