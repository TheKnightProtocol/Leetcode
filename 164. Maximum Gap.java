import java.util.Arrays;

class Solution {
    public int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;

        int minNum = Integer.MAX_VALUE, maxNum = Integer.MIN_VALUE;
        for (int num : nums) {
            minNum = Math.min(minNum, num);
            maxNum = Math.max(maxNum, num);
        }
        if (minNum == maxNum) return 0; // All elements are the same

        // Compute bucket size and count
        int n = nums.length;
        int bucketSize = Math.max(1, (maxNum - minNum) / (n - 1)); // Avoid zero size
        int bucketCount = (maxNum - minNum) / bucketSize + 1;

        // Initialize bucket min and max arrays
        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);
        boolean[] bucketUsed = new boolean[bucketCount];

        // Place elements in buckets
        for (int num : nums) {
            int bucketIdx = (num - minNum) / bucketSize;
            bucketMin[bucketIdx] = Math.min(bucketMin[bucketIdx], num);
            bucketMax[bucketIdx] = Math.max(bucketMax[bucketIdx], num);
            bucketUsed[bucketIdx] = true;
        }

        // Compute max gap between non-empty buckets
        int maxGap = 0, prevMax = minNum;
        for (int i = 0; i < bucketCount; i++) {
            if (!bucketUsed[i]) continue; // Skip empty buckets
            maxGap = Math.max(maxGap, bucketMin[i] - prevMax);
            prevMax = bucketMax[i];
        }

        return maxGap;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.maximumGap(new int[]{3,6,9,1})); // Output: 3
        System.out.println(sol.maximumGap(new int[]{10})); // Output: 0
    }
}
