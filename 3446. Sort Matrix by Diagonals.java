import java.util.*;

class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        for (int k = 0; k < n; k++) {
            List<Integer> diag = new ArrayList<>();
            for (int i = k, j = 0; i < n && j < n; i++, j++) diag.add(grid[i][j]);
            diag.sort(Collections.reverseOrder());
            for (int i = k, j = 0, p = 0; i < n && j < n; i++, j++, p++) grid[i][j] = diag.get(p);
        }
        for (int k = 1; k < n; k++) {
            List<Integer> diag = new ArrayList<>();
            for (int i = 0, j = k; i < n && j < n; i++, j++) diag.add(grid[i][j]);
            diag.sort(Comparator.naturalOrder());
            for (int i = 0, j = k, p = 0; i < n && j < n; i++, j++, p++) grid[i][j] = diag.get(p);
        }
        return grid;
    }
}
