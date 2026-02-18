class Solution {
private:
    int n;
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
    
    int getIndex(int i, int j) {
        return i * n + j;
    }
    
public:
    int largestIsland(vector<vector<int>>& grid) {
        n = grid.size();
        parent.resize(n * n);
        size.resize(n * n, 1);
        
        for (int i = 0; i < n * n; i++) {
            parent[i] = i;
        }
        
        int dirs[5] = {0, 1, 0, -1, 0};
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dirs[d];
                        int nj = j + dirs[d + 1];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < n && grid[ni][nj] == 1) {
                            unite(getIndex(i, j), getIndex(ni, nj));
                        }
                    }
                }
            }
        }
        
        int maxIsland = 0;
        for (int i = 0; i < n * n; i++) {
            if (parent[i] == i && grid[i / n][i % n] == 1) {
                maxIsland = max(maxIsland, size[i]);
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    unordered_set<int> neighbors;
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dirs[d];
                        int nj = j + dirs[d + 1];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < n && grid[ni][nj] == 1) {
                            neighbors.insert(find(getIndex(ni, nj)));
                        }
                    }
                    
                    int total = 1;
                    for (int root : neighbors) {
                        total += size[root];
                    }
                    maxIsland = max(maxIsland, total);
                }
            }
        }
        
        return maxIsland;
    }
};
