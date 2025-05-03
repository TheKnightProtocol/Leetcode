import java.util.*;

public class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int max = Integer.MIN_VALUE;

        // Fix left and right columns
        for (int left = 0; left < n; left++) {
            int[] rowSum = new int[m];

            for (int right = left; right < n; right++) {
                // Compress 2D to 1D
                for (int i = 0; i < m; i++) {
                    rowSum[i] += matrix[i][right];
                }

                // Find the max subarray no more than K
                max = Math.max(max, getMaxSumNoMoreThanK(rowSum, k));
            }
        }

        return max;
    }

    // Helper function: max subarray sum ≤ k
    private int getMaxSumNoMoreThanK(int[] nums, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int max = Integer.MIN_VALUE, prefixSum = 0;

        for (int num : nums) {
            prefixSum += num;

            Integer target = set.ceiling(prefixSum - k); // smallest prefix ≥ prefixSum - k
            if (target != null) {
                max = Math.max(max, prefixSum - target);
            }

            set.add(prefixSum);
        }

        return max;
    }
}
