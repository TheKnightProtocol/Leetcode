class Solution {
private:
    vector<int> parent;
    vector<int> size;
    
    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    void unite(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (size[rootX] < size[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
        }
    }
    
    int getIndex(int i, int j, int n) {
        return i * n + j;
    }
    
public:
    vector<int> hitBricks(vector<vector<int>>& grid, vector<vector<int>>& hits) {
        int m = grid.size(), n = grid[0].size();
        int total = m * n;
        parent.resize(total + 1);
        size.resize(total + 1, 1);
        for (int i = 0; i <= total; i++) {
            parent[i] = i;
        }
        
        vector<vector<int>> copy = grid;
        for (auto& hit : hits) {
            copy[hit[0]][hit[1]] = 0;
        }
        
        int top = total;
        for (int j = 0; j < n; j++) {
            if (copy[0][j] == 1) {
                unite(j, top);
            }
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] == 1) {
                    if (copy[i-1][j] == 1) {
                        unite(getIndex(i, j, n), getIndex(i-1, j, n));
                    }
                    if (j > 0 && copy[i][j-1] == 1) {
                        unite(getIndex(i, j, n), getIndex(i, j-1, n));
                    }
                }
            }
        }
        
        vector<int> result(hits.size(), 0);
        int dirs[5] = {0, 1, 0, -1, 0};
        
        for (int k = hits.size() - 1; k >= 0; k--) {
            int i = hits[k][0], j = hits[k][1];
            if (grid[i][j] == 0) {
                continue;
            }
            
            int prevTop = size[find(top)];
            
            if (i == 0) {
                unite(j, top);
            }
            
            for (int d = 0; d < 4; d++) {
                int ni = i + dirs[d];
                int nj = j + dirs[d + 1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && copy[ni][nj] == 1) {
                    unite(getIndex(i, j, n), getIndex(ni, nj, n));
                }
            }
            
            int newTop = size[find(top)];
            result[k] = max(0, newTop - prevTop - 1);
            
            copy[i][j] = 1;
        }
        
        return result;
    }
};
