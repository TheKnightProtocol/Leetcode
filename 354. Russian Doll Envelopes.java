import java.util.*;

public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        // Step 1: Sort envelopes
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0])
                return b[1] - a[1]; // descending height if width is same
            return a[0] - b[0];     // ascending width
        });

        // Step 2: Extract heights and find LIS
        int[] dp = new int[envelopes.length];
        int len = 0;

        for (int[] env : envelopes) {
            int height = env[1];
            int idx = Arrays.binarySearch(dp, 0, len, height);
            if (idx < 0) idx = -(idx + 1);  // insertion point

            dp[idx] = height;
            if (idx == len) len++;  // found a new LIS element
        }

        return len;
    }
}
