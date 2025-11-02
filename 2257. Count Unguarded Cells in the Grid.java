import java.util.*;

class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        char[][] grid = new char[m][n];
        
        for (int[] guard : guards) {
            grid[guard[0]][guard[1]] = 'G';
        }
        
        for (int[] wall : walls) {
            grid[wall[0]][wall[1]] = 'W';
        }
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        for (int[] guard : guards) {
            int r = guard[0];
            int c = guard[1];
            
            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                while (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] != 'W' && grid[nr][nc] != 'G') {
                    if (grid[nr][nc] != 'S') {
                        grid[nr][nc] = 'S';
                    }
                    nr += dir[0];
                    nc += dir[1];
                }
            }
        }
        
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    count++;
                }
            }
        }
        
        return count;
    }
}
