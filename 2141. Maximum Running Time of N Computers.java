import java.util.*;

class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long total = 0;
        for (int b : batteries) {
            total += b;
        }
        
        long left = 1, right = total / n;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            
            if (canRun(n, batteries, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return right;
    }
    
    private boolean canRun(int n, int[] batteries, long time) {
        long total = 0;
        for (int b : batteries) {
            total += Math.min(b, time);
            if (total >= time * n) {
                return true;
            }
        }
        return total >= time * n;
    }
}
