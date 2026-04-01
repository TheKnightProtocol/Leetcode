class Solution {
public:
    vector<int> survivedRobotsHealths(vector<int>& positions, vector<int>& healths, string directions) {
        int n = positions.size();
        vector<vector<int>> robots;
        
        for (int i = 0; i < n; i++) {
            robots.push_back({positions[i], healths[i], directions[i] == 'R' ? 1 : 0, i});
        }
        
        sort(robots.begin(), robots.end());
        
        vector<vector<int>> stack;
        
        for (auto& robot : robots) {
            int health = robot[1];
            int direction = robot[2];
            int idx = robot[3];
            
            if (direction == 1) {
                stack.push_back({health, direction, idx});
            } else {
                bool destroyed = false;
                
                while (!stack.empty() && stack.back()[1] == 1) {
                    if (stack.back()[0] > health) {
                        stack.back()[0]--;
                        destroyed = true;
                        break;
                    } else if (stack.back()[0] < health) {
                        health--;
                        stack.pop_back();
                    } else {
                        stack.pop_back();
                        destroyed = true;
                        break;
                    }
                }
                
                if (!destroyed) {
                    stack.push_back({health, direction, idx});
                }
            }
        }
        
        sort(stack.begin(), stack.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[2] < b[2];
        });
        
        vector<int> result;
        for (auto& robot : stack) {
            result.push_back(robot[0]);
        }
        
        return result;
    }
};
