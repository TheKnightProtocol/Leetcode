import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int specialTriplets(int[] nums) {
        int n = nums.length;
        long total = 0;
        
        // Count frequency of each value in the entire array
        Map<Integer, Integer> totalFreq = new HashMap<>();
        for (int num : nums) {
            totalFreq.put(num, totalFreq.getOrDefault(num, 0) + 1);
        }
        
        // Track frequency of elements on the left and right of current j
        Map<Integer, Integer> leftFreq = new HashMap<>();
        Map<Integer, Integer> rightFreq = new HashMap<>(totalFreq);
        
        for (int j = 0; j < n; j++) {
            int current = nums[j];
            int target = current * 2;
            
            // Update rightFreq: remove current element from right side
            rightFreq.put(current, rightFreq.get(current) - 1);
            if (rightFreq.get(current) == 0) {
                rightFreq.remove(current);
            }
            
            // Count special triplets for this j
            if (leftFreq.containsKey(target) && rightFreq.containsKey(target)) {
                long leftCount = leftFreq.get(target);
                long rightCount = rightFreq.get(target);
                total = (total + leftCount * rightCount) % MOD;
            }
            
            // Add current element to left side for next iteration
            leftFreq.put(current, leftFreq.getOrDefault(current, 0) + 1);
        }
        
        return (int) total;
    }
}
