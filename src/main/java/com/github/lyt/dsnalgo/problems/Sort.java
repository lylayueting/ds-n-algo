package com.github.lyt.dsnalgo.problems;

public class Sort {

  /**
   * Bubble Sort
   * <br>
   * Time complexity: O(N*N). <br>
   * Space complexity: Constant.
   */
  public static void bubbleSort(int[] array) {
    int len = array.length;
    for (int i = len - 1; i >= 1; i--) {
      for (int j = 0; j < i; j++) {
        if (array[j] > array[j + 1]) {
          int temp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = temp;
        }
      }
    }
  }

  /**
   * Selection Sort
   * <br>
   * Time complexity: O(N*N). <br>
   * Space complexity: Constant.
   */
  public static void selectionSort(int[] array) {

    int len = array.length;
    for (int i = 0; i < len - 1; i++) {
      // Minimize number of reading and writing to array;
      int min = array[i], minIndex = i;
      for (int j = i + 1; j < len; j++) {
        if (array[j] < min) {
          min = array[j];
          minIndex = j;
        }
      }

      array[minIndex] = array[i];
      array[i] = min;
    }
  }

  /**
   * Selection Sort
   * <br/>
   * Time complexity: best case N, worst case O(N*N), average O(N*N). <br />
   * Space complexity: Constant.
   */
  public static void insertionSort(int[] array) {
    int len = array.length;

    for (int i = 1; i < len; i++) {
      int current = array[i], j = i;
      while (j > 0 && current < array[j - 1]) {
        array[j] = array[j - 1];
        j--;
      }
      array[j] = current;
    }
  }

  /**
   * Function to allow customization of gap sequence, thus adjusting time complexity of Shell Sort.
   * <br/>
   * When using N/2^k as sequence, worst case time complexity is O(N*N);<br/>
   * When using 2^k-1 as sequence, worst case time complexity is O(N^(3/2));<br/>
   */
  private static int gapSequence(int length, int round) {
    return length / (int) Math.pow(2, round);
  }

  /**
   * Shell Sort
   * <br/>
   * Unstable <br/>
   * Space complexity: constant. <br/>
   */
  public static void shellSort(int[] array) {
    int len = array.length, round = 1;
    int gap = gapSequence(len, round);

    while (gap > 0) {
      for (int hSortToLeft = gap; hSortToLeft < len; hSortToLeft++) {
        int current = array[hSortToLeft], j = hSortToLeft;
        while (j >= gap && current < array[j - gap]) {
          array[j] = array[j - gap];
          j -= gap;
        }
        array[j] = current;
      }
      gap = gapSequence(len, ++round);
    }
  }
}
