import java.util.*;

class Solution {
    public long maximumTotalDamage(int[] power) {
        // Count frequency of each damage
        Map<Integer, Integer> freq = new HashMap<>();
        for (int p : power) {
            freq.put(p, freq.getOrDefault(p, 0) + 1);
        }
        
        // Get unique damage values and sort them
        List<Integer> unique = new ArrayList<>(freq.keySet());
        Collections.sort(unique);
        
        int n = unique.size();
        long[] dp = new long[n + 1];
        
        for (int i = 1; i <= n; i++) {
            int currentDamage = unique.get(i - 1);
            long currentTotal = (long) freq.get(currentDamage) * currentDamage;
            
            // Option 1: Skip current damage
            dp[i] = dp[i - 1];
            
            // Option 2: Take current damage
            // Find the largest index j where unique[j] < currentDamage - 2
            int left = 0, right = i - 1;
            int bestIndex = -1;
            
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (unique.get(mid) < currentDamage - 2) {
                    bestIndex = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            
            if (bestIndex != -1) {
                dp[i] = Math.max(dp[i], currentTotal + dp[bestIndex + 1]);
            } else {
                dp[i] = Math.max(dp[i], currentTotal);
            }
        }
        
        return dp[n];
    }
}
