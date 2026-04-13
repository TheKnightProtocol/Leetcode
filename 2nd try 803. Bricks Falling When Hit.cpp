class DSU {
    vector<int> parent, size;
public:
    DSU(int n) {
        parent.resize(n);
        size.resize(n, 1);
        for (int i = 0; i < n; i++) parent[i] = i;
    }
    
    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    
    void unite(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx == ry) return;
        if (size[rx] < size[ry]) swap(rx, ry);
        parent[ry] = rx;
        size[rx] += size[ry];
    }
    
    int getSize(int x) {
        return size[find(x)];
    }
};

class Solution {
public:
    vector<int> hitBricks(vector<vector<int>>& grid, vector<vector<int>>& hits) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> gridCopy = grid;
        
        for (auto& hit : hits) {
            gridCopy[hit[0]][hit[1]] = 0;
        }
        
        DSU dsu(m * n + 1);
        int virtualNode = m * n;
        
        auto index = [&](int r, int c) { return r * n + c; };
        
        int dirs[4][2] = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (gridCopy[i][j] == 1) {
                    if (i == 0) dsu.unite(index(i, j), virtualNode);
                    if (i > 0 && gridCopy[i-1][j] == 1) dsu.unite(index(i, j), index(i-1, j));
                    if (j > 0 && gridCopy[i][j-1] == 1) dsu.unite(index(i, j), index(i, j-1));
                }
            }
        }
        
        vector<int> result(hits.size());
        
        for (int k = hits.size() - 1; k >= 0; k--) {
            int r = hits[k][0], c = hits[k][1];
            
            if (grid[r][c] == 0) {
                result[k] = 0;
                continue;
            }
            
            int prevStable = dsu.getSize(virtualNode);
            
            gridCopy[r][c] = 1;
            int idx = index(r, c);
            
            if (r == 0) dsu.unite(idx, virtualNode);
            
            for (auto& dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && gridCopy[nr][nc] == 1) {
                    dsu.unite(idx, index(nr, nc));
                }
            }
            
            int newStable = dsu.getSize(virtualNode);
            result[k] = max(0, newStable - prevStable - 1);
        }
        
        return result;
    }
};
