#include <vector>
using namespace std;

class Solution {
public:
    void gameOfLife(vector<vector<int>>& board) {
        int m = board.size();
        int n = board[0].size();
        int dirs[8][2] = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int liveNeighbors = 0;
                for (auto& d : dirs) {
                    int ni = i + d[0];
                    int nj = j + d[1];
                    if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                        if (board[ni][nj] & 1) liveNeighbors++;
                    }
                }
                int current = board[i][j] & 1;
                int next = 0;
                if (current == 1) {
                    if (liveNeighbors == 2 || liveNeighbors == 3) next = 1;
                } else {
                    if (liveNeighbors == 3) next = 1;
                }
                board[i][j] |= (next << 1);
            }
        }
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                board[i][j] >>= 1;
            }
        }
    }
};
