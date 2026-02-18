class Solution {
public:
    int racecar(int target) {
        queue<pair<int, int>> q; // {position, speed}
        unordered_set<string> visited;
        
        q.push({0, 1});
        visited.insert("0,1");
        
        int steps = 0;
        
        while (!q.empty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                auto [pos, speed] = q.front();
                q.pop();
                
                if (pos == target) {
                    return steps;
                }
                
                // Accelerate
                int newPos = pos + speed;
                int newSpeed = speed * 2;
                string key = to_string(newPos) + "," + to_string(newSpeed);
                if (abs(newPos) <= 2 * target && !visited.count(key)) {
                    visited.insert(key);
                    q.push({newPos, newSpeed});
                }
                
                // Reverse
                newSpeed = (speed > 0) ? -1 : 1;
                key = to_string(pos) + "," + to_string(newSpeed);
                if (!visited.count(key)) {
                    visited.insert(key);
                    q.push({pos, newSpeed});
                }
            }
            
            steps++;
        }
        
        return -1;
    }
};
