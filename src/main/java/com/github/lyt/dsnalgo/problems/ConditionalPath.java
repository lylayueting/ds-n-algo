package com.github.lyt.dsnalgo.problems;

import java.util.Stack;

public class ConditionalPath {

  private boolean[][] visited;

  private static class Position {

    public int x;
    public int y;

    public Position(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public int movingCount(int threshold, int rows, int cols) {

    if (rows <= 0 || cols <= 0 || threshold < 0) {
      return 0;
    }

    visited = new boolean[rows][cols];

    visited[0][0] = true;

    Stack<Position> stack = new Stack<>();
    stack.push(new Position(0, 0));

    while (!stack.empty()) {
      Position pos = stack.pop();

      if (pos.x - 1 >= 0 && !visited[pos.x - 1][pos.y] && (bitSum(pos.x - 1) + bitSum(pos.y)
          <= threshold)) {
        visited[pos.x - 1][pos.y] = true;
        stack.push(new Position(pos.x - 1, pos.y));
      }

      if (pos.x + 1 < rows && !visited[pos.x + 1][pos.y] && (bitSum(pos.x + 1) + bitSum(pos.y)
          <= threshold)) {
        visited[pos.x + 1][pos.y] = true;
        stack.push(new Position(pos.x + 1, pos.y));
      }

      if (pos.y - 1 >= 0 && !visited[pos.x][pos.y - 1] && (bitSum(pos.x) + bitSum(pos.y - 1)
          <= threshold)) {
        visited[pos.x][pos.y - 1] = true;
        stack.push(new Position(pos.x, pos.y - 1));
      }

      if (pos.y + 1 < cols && !visited[pos.x][pos.y + 1] && (bitSum(pos.x) + bitSum(pos.y + 1)
          <= threshold)) {
        visited[pos.x][pos.y + 1] = true;
        stack.push(new Position(pos.x, pos.y + 1));
      }
    }

    int count = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (visited[i][j]) {
          count++;
        }
      }
    }
    return count;
  }

  private int bitSum(int number) {
    int sum = 0, n = number;

    while (n != 0) {
      sum += (n % 10);
      n = n / 10;
    }

    return sum;
  }

  public static void main(String[] args) {
    ConditionalPath solution = new ConditionalPath();

    System.out.println(solution.movingCount(0, 0, 0));
    System.out.println(solution.movingCount(0, 1, 1));
    System.out.println(solution.movingCount(10, 12, 12));
  }
}