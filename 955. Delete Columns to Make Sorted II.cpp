#include <vector>
#include <string>
using namespace std;

class Solution {
public:
    int minDeletionSize(vector<string>& strs) {
        int rows = strs.size();
        int cols = strs[0].size();
        int deletions = 0;
        vector<bool> satisfied(rows - 1, false);
        
        for (int c = 0; c < cols; c++) {
            bool needDelete = false;
            
            // Check if current column violates order for any unsatisfied pairs
            for (int r = 0; r < rows - 1; r++) {
                if (!satisfied[r] && strs[r][c] > strs[r + 1][c]) {
                    needDelete = true;
                    break;
                }
            }
            
            if (needDelete) {
                deletions++;
            } else {
                // Mark pairs that become satisfied by this column
                for (int r = 0; r < rows - 1; r++) {
                    if (!satisfied[r] && strs[r][c] < strs[r + 1][c]) {
                        satisfied[r] = true;
                    }
                }
            }
        }
        
        return deletions;
    }
};
