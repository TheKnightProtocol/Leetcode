import java.util.*;

class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        int[][][] dirs = {
            {},
            {{0, 1}, {0, -1}},
            {{1, 0}, {-1, 0}},
            {{0, -1}, {1, 0}},
            {{0, 1}, {1, 0}},
            {{0, -1}, {-1, 0}},
            {{0, 1}, {-1, 0}}
        };
        
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];
            if (r == m - 1 && c == n - 1) return true;
            int type = grid[r][c];
            for (int[] d : dirs[type]) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (visited[nr][nc]) continue;
                int needDr = -d[0];
                int needDc = -d[1];
                int ntype = grid[nr][nc];
                for (int[] nd : dirs[ntype]) {
                    if (nd[0] == needDr && nd[1] == needDc) {
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                        break;
                    }
                }
            }
        }
        return false;
    }
}
