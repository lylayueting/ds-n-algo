package com.github.lyt.dsnalgo.problems;

/**
 * Created by Yueting on 6/26/2017.
 */
public class KthLargest {

  public static int findKthSmallest(int[] nums, int k) {
    assert k >= 1 && nums.length >= k;

    int len = nums.length;
    int low = 0, high = len - 1, cut = len;

    while (true) {
      if (k - 1 == cut) {
        return nums[cut];
      }

      if (k - 1 < cut) {
        high = cut - 1;
      } else {
        low = cut + 1;
      }

      cut = partition(nums, low, high);

      System.out.print("After swap with pivot " + nums[cut] + ": ");
      for (int n : nums) {
        System.out.print(n + " ");
      }
      System.out.println();
    }
  }

  private static int partition(int[] nums, int low, int high) {
    assert nums != null;

    int pivot = nums[low];
    int smaller = low + 1, largerOrEqual = high;

    while (smaller < largerOrEqual) {
      while (largerOrEqual >= smaller && nums[largerOrEqual] >= pivot) {
        largerOrEqual--;
      }
      while (smaller < largerOrEqual && nums[smaller] < pivot) {
        smaller++;
      }

      if (smaller < largerOrEqual) {
        int temp = nums[largerOrEqual];
        nums[largerOrEqual] = nums[smaller];
        nums[smaller] = temp;
        largerOrEqual--;
        smaller++;
      }
    }

    if (pivot > nums[largerOrEqual]) {
      nums[low] = nums[largerOrEqual];
      nums[largerOrEqual] = pivot;
    }

    return largerOrEqual;
  }

  public static int findKthLargest(int[] nums, int k) {
    return findKthSmallest(nums, nums.length - k + 1);
  }
}
