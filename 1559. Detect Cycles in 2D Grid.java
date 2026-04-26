import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!visited[i][j]) {
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j, -1, -1});
                    visited[i][j] = true;

                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        int r = cur[0], c = cur[1], pr = cur[2], pc = cur[3];
                        char ch = grid[r][c];

                        for (int[] d : dirs) {
                            int nr = r + d[0], nc = c + d[1];
                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == ch) {
                                if (!visited[nr][nc]) {
                                    visited[nr][nc] = true;
                                    q.offer(new int[]{nr, nc, r, c});
                                } else {
                                    if (nr != pr || nc != pc) return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
