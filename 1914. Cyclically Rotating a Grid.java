import java.util.*;

class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int layers = Math.min(m, n) / 2;
        for (int i = 0; i < layers; i++) {
            int top = i, bottom = m - 1 - i;
            int left = i, right = n - 1 - i;
            List<Integer> layer = new ArrayList<>();
            for (int j = left; j <= right; j++) layer.add(grid[top][j]);
            for (int j = top + 1; j <= bottom; j++) layer.add(grid[j][right]);
            for (int j = right - 1; j >= left; j--) layer.add(grid[bottom][j]);
            for (int j = bottom - 1; j >= top + 1; j--) layer.add(grid[j][left]);
            int len = layer.size();
            int shift = k % len;
            Collections.rotate(layer, -shift);
            int idx = 0;
            for (int j = left; j <= right; j++) grid[top][j] = layer.get(idx++);
            for (int j = top + 1; j <= bottom; j++) grid[j][right] = layer.get(idx++);
            for (int j = right - 1; j >= left; j--) grid[bottom][j] = layer.get(idx++);
            for (int j = bottom - 1; j >= top + 1; j--) grid[j][left] = layer.get(idx++);
        }
        return grid;
    }
}
