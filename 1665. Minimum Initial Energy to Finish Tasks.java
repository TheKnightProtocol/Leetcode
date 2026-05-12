import java.util.*;

class Solution {
    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));
        int ans = 0, curr = 0;
        for (int[] t : tasks) {
            int a = t[0], m = t[1];
            if (curr < m) {
                ans += (m - curr);
                curr = m;
            }
            curr -= a;
        }
        return ans;
    }
}
