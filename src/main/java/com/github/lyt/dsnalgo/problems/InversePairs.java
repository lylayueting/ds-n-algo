package com.github.lyt.dsnalgo.problems;

public class InversePairs {

  public static int inversePairs(int[] array) {
    if (array == null || array.length <= 1) {
      return 0;
    }

    int low = 0, high = array.length - 1;
    return (int) (inversePairsRecursive(array, low, high) % 1000000007);
  }

  // Use merge sort method, assume two halves are sorted, and count
  // reverse pairs between two halves.
  // @return number of reverse pairs between two halves.
  private static long inversePairsRecursive(int[] array, int low, int high) {
    if (low == high) {
      return 0;
    }

    long numPairs = 0;
    int mid = (low + high) / 2;

    numPairs += inversePairsRecursive(array, low, mid);
    numPairs += inversePairsRecursive(array, mid + 1, high);

    // Then merge two sorted halves.
    int[] swap = new int[high - low + 1];
    int left = low, right = mid + 1;
    long count = 0;

    for (int i = 0; i < swap.length; i++) {
      if (left > mid) {
        // When i exceeds swap.length, right should be greater than high.
        swap[i] = array[right++];
        continue;
      }

      if (right > high) {
        // Likewise, when i exceeds swap.length, left should be greater than mid.
        swap[i] = array[left++];
        // Count doesn't change because it's calculated when array[right] < array[left].
        continue;
      }

      if (array[left] <= array[right]) {
        swap[i] = array[left++];
        continue;
      }

      // when array[left] > array[right]
      count += mid - left + 1;
      swap[i] = array[right++];
    }

    System.arraycopy(swap, 0, array, low, swap.length);

//    System.out.println(String.format("Counted between %d and %d, there are %d inverse pairs.", low, high, count));

    return numPairs += count;
  }
}