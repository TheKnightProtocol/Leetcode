class Solution {
public:
    int containVirus(vector<vector<int>>& isInfected) {
        int m = isInfected.size(), n = isInfected[0].size();
        int walls = 0;
        vector<int> dirs = {0, 1, 0, -1, 0};
        
        while (true) {
            vector<vector<bool>> visited(m, vector<bool>(n, false));
            vector<set<pair<int, int>>> regions;
            vector<set<pair<int, int>>> frontiers;
            vector<int> wallsNeeded;
            
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (isInfected[i][j] == 1 && !visited[i][j]) {
                        set<pair<int, int>> region;
                        set<pair<int, int>> frontier;
                        int wallsForRegion = 0;
                        queue<pair<int, int>> q;
                        
                        q.push({i, j});
                        visited[i][j] = true;
                        
                        while (!q.empty()) {
                            auto [x, y] = q.front();
                            q.pop();
                            region.insert({x, y});
                            
                            for (int d = 0; d < 4; d++) {
                                int nx = x + dirs[d];
                                int ny = y + dirs[d + 1];
                                
                                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                                    if (isInfected[nx][ny] == 1 && !visited[nx][ny]) {
                                        visited[nx][ny] = true;
                                        q.push({nx, ny});
                                    } else if (isInfected[nx][ny] == 0) {
                                        frontier.insert({nx, ny});
                                        wallsForRegion++;
                                    }
                                }
                            }
                        }
                        
                        if (!frontier.empty()) {
                            regions.push_back(region);
                            frontiers.push_back(frontier);
                            wallsNeeded.push_back(wallsForRegion);
                        }
                    }
                }
            }
            
            if (regions.empty()) break;
            
            int maxIdx = 0;
            for (int i = 1; i < frontiers.size(); i++) {
                if (frontiers[i].size() > frontiers[maxIdx].size()) {
                    maxIdx = i;
                }
            }
            
            walls += wallsNeeded[maxIdx];
            
            for (int i = 0; i < regions.size(); i++) {
                if (i == maxIdx) {
                    for (auto [x, y] : regions[i]) {
                        isInfected[x][y] = 2;
                    }
                } else {
                    for (auto [x, y] : frontiers[i]) {
                        isInfected[x][y] = 1;
                    }
                }
            }
        }
        
        return walls;
    }
};
