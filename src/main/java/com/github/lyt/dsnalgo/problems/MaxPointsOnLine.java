package com.github.lyt.dsnalgo.problems;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yueting on 6/27/2017.
 */
public class MaxPointsOnLine {

  protected static class Point {

    private int x;
    private int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public static int maxPoints(Point[] points) {
    if (points == null || points.length == 0) {
      return 0;
    }

    // Max number of points for every point.
    Map<Point, Integer> maxForAll = new HashMap<>();
    int len = points.length;

    for (int i = 0; i < len; i++) {
      Point current = points[i];
      Map<Double, Integer> maxForOne = new HashMap<>();
      int vertical = 0, duplicate = 0;

      for (int j = i + 1; j < len; j++) {
        Point point = points[j];

        if ((current.x - point.x) == 0 && (current.y - point.y) == 0) {
          duplicate++;
          continue;
        }

        if ((current.x - point.x) == 0) {
          vertical++;
          continue;
        }

        Double slope = 1d * (point.y - current.y) / (point.x - current.x) + 0.0d;
        if (maxForOne.containsKey(slope)) {
          maxForOne.put(slope, maxForOne.get(slope) + 1);
        } else {
          maxForOne.put(slope, 1);
        }
      }

      // Pick one slope with max number of points with current center
      int localMax = 0;
      for (int value : maxForOne.values()) {
        if (value > localMax) {
          localMax = value;
        }
      }

      maxForAll.put(current, duplicate + 1 + (localMax > vertical ? localMax : vertical));
    }

    // Pick one slope with max number of points for all
    int max = 0;
    for (int value : maxForAll.values()) {
      if (value > max) {
        max = value;
      }
    }

    return max;
  }
}