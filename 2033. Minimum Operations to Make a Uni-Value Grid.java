import java.util.Arrays;

class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        int[] values = new int[total];
        
        int rem = grid[0][0] % x;
        int idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                if (val % x != rem) {
                    return -1;
                }
                values[idx++] = val / x;
            }
        }
        
        Arrays.sort(values);
        int median = values[total / 2];
        
        long operations = 0;
        for (int v : values) {
            operations += Math.abs(v - median);
        }
        return (int) operations;
    }
}
