import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    private static final int MOD = 1000000007;
    
    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        
        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();
        
        int sum = dp[0];
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[right]) {
                maxDeque.pollLast();
            }
            maxDeque.offerLast(right);
            
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[right]) {
                minDeque.pollLast();
            }
            minDeque.offerLast(right);
            
            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > k) {
                sum = (sum - dp[left]) % MOD;
                if (sum < 0) sum += MOD;
                left++;
                
                if (maxDeque.peekFirst() < left) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() < left) {
                    minDeque.pollFirst();
                }
            }
            
            dp[right + 1] = sum;
            sum = (sum + dp[right + 1]) % MOD;
        }
        
        return dp[n];
    }
}
