class Solution {
public:
    int uniquePathsIII(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int startX, startY, endX, endY;
        int emptyCount = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                } else if (grid[i][j] == 2) {
                    endX = i;
                    endY = j;
                } else if (grid[i][j] == 0) {
                    emptyCount++;
                }
            }
        }
        
        int result = 0;
        vector<vector<bool>> visited(m, vector<bool>(n, false));
        
        function<void(int, int, int)> dfs = [&](int x, int y, int walked) {
            if (x == endX && y == endY) {
                if (walked == emptyCount + 2) result++;
                return;
            }
            
            visited[x][y] = true;
            
            int dirs[4][2] = {{1,0}, {-1,0}, {0,1}, {0,-1}};
            for (auto& dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && grid[nx][ny] != -1) {
                    dfs(nx, ny, walked + 1);
                }
            }
            
            visited[x][y] = false;
        };
        
        dfs(startX, startY, 1);
        return result;
    }
};
