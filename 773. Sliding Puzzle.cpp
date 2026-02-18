class Solution {
public:
    int slidingPuzzle(vector<vector<int>>& board) {
        string target = "123450";
        string start = "";
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                start += to_string(board[i][j]);
            }
        }
        
        vector<vector<int>> neighbors = {
            {1, 3},
            {0, 2, 4},
            {1, 5},
            {0, 4},
            {1, 3, 5},
            {2, 4}
        };
        
        queue<string> q;
        unordered_set<string> visited;
        
        q.push(start);
        visited.insert(start);
        
        int moves = 0;
        
        while (!q.empty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                string current = q.front();
                q.pop();
                
                if (current == target) {
                    return moves;
                }
                
                int zeroPos = current.find('0');
                
                for (int neighborPos : neighbors[zeroPos]) {
                    string next = current;
                    swap(next[zeroPos], next[neighborPos]);
                    
                    if (!visited.count(next)) {
                        visited.insert(next);
                        q.push(next);
                    }
                }
            }
            
            moves++;
        }
        
        return -1;
    }
};
