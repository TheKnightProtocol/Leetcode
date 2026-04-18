
#include <vector>
#include <string>
#include <unordered_set>
#include <algorithm>

using namespace std;

class Solution {
public:
    int robotSim(vector<int>& commands, vector<vector<int>>& obstacles) {
        vector<pair<int, int>> dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dir = 0;
        int x = 0, y = 0;
        
        unordered_set<string> obstacleSet;
        for (const auto& obs : obstacles) {
            obstacleSet.insert(to_string(obs[0]) + "," + to_string(obs[1]));
        }
        
        int maxDistSq = 0;
        
        for (int cmd : commands) {
            if (cmd == -2) {
                dir = (dir + 3) % 4;
            } else if (cmd == -1) {
                dir = (dir + 1) % 4;
            } else {
                for (int step = 0; step < cmd; ++step) {
                    int nextX = x + dirs[dir].first;
                    int nextY = y + dirs[dir].second;
                    
                    string nextPos = to_string(nextX) + "," + to_string(nextY);
                    if (obstacleSet.find(nextPos) != obstacleSet.end()) {
                        break;
                    }
                    
                    x = nextX;
                    y = nextY;
                    maxDistSq = max(maxDistSq, x*x + y*y);
                }
            }
        }
        
        return maxDistSq;
    }
};
