package com.github.lyt.dsnalgo.problems;

import org.junit.Test;

/**
 * Created by Yueting on 6/26/2017.
 */
public class KthLargestTest {

  @Test
  public void TestFindKthSmallest() {
    int[] nums = {3, 2, 1, 5, 6, 4};
    int k = 6;

    System.out.println(k + "-th smallest number is " + KthLargest.findKthSmallest(nums, k));
  }
}
