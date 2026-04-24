import java.util.*;

class Solution {
    public int minWastedSpace(int[] packages, int[][] boxes) {
        Arrays.sort(packages);
        long n = packages.length, minWaste = Long.MAX_VALUE, mod = (long)1e9 + 7, sumP = 0;
        long[] prefix = new long[(int)n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + packages[i];
        
        for (int[] b : boxes) {
            Arrays.sort(b);
            if (b[b.length - 1] < packages[(int)n - 1]) continue;
            long curWaste = 0, lastIdx = 0;
            for (int size : b) {
                int idx = Arrays.binarySearch(packages, (int)lastIdx, (int)n, size);
                if (idx < 0) idx = -idx - 2;
                else while (idx < n - 1 && packages[idx + 1] == size) idx++;
                
                curWaste += (long)size * (idx + 1 - lastIdx) - (prefix[idx + 1] - prefix[(int)lastIdx]);
                lastIdx = idx + 1;
            }
            minWaste = Math.min(minWaste, curWaste);
        }
        return minWaste == Long.MAX_VALUE ? -1 : (int)(minWaste % mod);
    }
}
