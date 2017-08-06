package com.github.lyt.dsnalgo.problems;

/**
 * Created by Yueting on 7/2/2017.
 */
public class PathInMatrix {

  private char[] matrix;
  private char[] str;
  private int rows;
  private int cols;

  public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
    if (matrix == null || matrix.length == 0 || str == null || str.length == 0) {
      return false;
    }

    if (rows <= 0 || cols <= 0) {
      return false;
    }

    if (matrix.length != rows * cols) {
      return false;
    }

    this.matrix = matrix;
    this.str = str;
    this.rows = rows;
    this.cols = cols;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        boolean[][] visited = new boolean[rows][cols];

        if (matrix[i * cols + j] == str[0] && findsPath(visited, i, j, 0)) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean findsPath(boolean[][] visited, int i, int j, int n) {
        /*if (i >= rows || j >= cols || i < 0 || j < 0) {
            return false;
        }*/

    if (matrix[i * cols + j] == str[n]) {
      visited[i][j] = true;

      return (n == str.length - 1) || (i - 1 >= 0 && !visited[i - 1][j] && findsPath(visited, i - 1,
          j, n + 1))
          || (i + 1 < rows && !visited[i + 1][j] && findsPath(visited, i + 1, j, n + 1))
          || (j - 1 >= 0 && !visited[i][j - 1] && findsPath(visited, i, j - 1, n + 1))
          || (j + 1 < cols && !visited[i][j + 1] && findsPath(visited, i, j + 1, n + 1));
    }

    visited[i][j] = false;
    return false;
  }
}
