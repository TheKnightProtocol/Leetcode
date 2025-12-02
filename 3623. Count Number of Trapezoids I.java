import java.util.*;

class Solution {
    public int countTrapezoids(int[][] points) {
        int MOD = 1000000007;
        Map<Integer, Integer> yCount = new HashMap<>();
        
        for (int[] point : points) {
            yCount.put(point[1], yCount.getOrDefault(point[1], 0) + 1);
        }
        
        long result = 0;
        List<Integer> counts = new ArrayList<>();
        for (int count : yCount.values()) {
            if (count >= 2) {
                counts.add(count);
            }
        }
        
        int n = counts.size();
        long[] pairCounts = new long[n];
        long totalPairs = 0;
        
        for (int i = 0; i < n; i++) {
            int c = counts.get(i);
            pairCounts[i] = (long) c * (c - 1) / 2 % MOD;
            totalPairs = (totalPairs + pairCounts[i]) % MOD;
        }
        
        for (int i = 0; i < n; i++) {
            result = (result + pairCounts[i] * (totalPairs - pairCounts[i]) % MOD) % MOD;
        }
        
        result = result * ((MOD + 1) / 2) % MOD;
        return (int) ((result + MOD) % MOD);
    }
}
