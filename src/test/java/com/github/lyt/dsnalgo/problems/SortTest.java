package com.github.lyt.dsnalgo.problems;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Created by Yueting on 6/12/2017.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class SortTest {

  private static final int[][] TEST_CASES = {
      {},
      {1},
      {5, 3},
      {0, 1, 4, 2, 8, 5, 7},
      {8, 7, 1, 4, 2, 0, 8, 5, 7},
      {35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13,
          12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}
  };

  private static final int[][] EXPECTED_RESULTS = {
      {},
      {1},
      {3, 5},
      {0, 1, 2, 4, 5, 7, 8},
      {0, 1, 2, 4, 5, 7, 7, 8, 8},
      {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
          26, 27, 28, 29, 30, 31, 32, 33, 34, 35}
  };

  @BeforeClass
  public static void testSetup() {
    Assert.assertEquals(TEST_CASES.length, EXPECTED_RESULTS.length);
  }

  @Test
  public void testBubbleSort() {

    for (int i = 0; i < TEST_CASES.length; i++) {
      int[] array = Arrays.copyOf(TEST_CASES[i], TEST_CASES[i].length);

      Sort.bubbleSort(array);

      Assert.assertArrayEquals(EXPECTED_RESULTS[i], array);
    }
  }

  @Test
  public void testSelectionSort() {

    for (int i = 0; i < TEST_CASES.length; i++) {
      int[] array = Arrays.copyOf(TEST_CASES[i], TEST_CASES[i].length);

      Sort.selectionSort(array);

      Assert.assertArrayEquals(EXPECTED_RESULTS[i], array);
    }
  }

  @Test
  public void testInsertionSort() {

    for (int i = 0; i < TEST_CASES.length; i++) {
      int[] array = Arrays.copyOf(TEST_CASES[i], TEST_CASES[i].length);

      Sort.insertionSort(array);

      Assert.assertArrayEquals(EXPECTED_RESULTS[i], array);
    }
  }

  @Test
  public void testShellSort() {

    for (int i = 0; i < TEST_CASES.length; i++) {
      int[] array = Arrays.copyOf(TEST_CASES[i], TEST_CASES[i].length);

      Sort.shellSort(array);

      Assert.assertArrayEquals(EXPECTED_RESULTS[i], array);
    }
  }

  @Test
  public void testMergeSort() {

    for (int i = 0; i < TEST_CASES.length; i++) {
      int[] array = Arrays.copyOf(TEST_CASES[i], TEST_CASES[i].length);

      Sort.mergeSortRecursive(array, 0, array.length - 1);

      Assert.assertArrayEquals(EXPECTED_RESULTS[i], array);
    }
  }
}
