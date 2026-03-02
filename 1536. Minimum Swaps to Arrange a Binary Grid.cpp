class Solution {
public:
    int minSwaps(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<int> rightmost(n);
        
        for (int i = 0; i < n; i++) {
            int pos = -1;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    pos = j;
                }
            }
            rightmost[i] = pos;
        }
        
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            int found = -1;
            for (int j = i; j < n; j++) {
                if (rightmost[j] <= i) {
                    found = j;
                    break;
                }
            }
            
            if (found == -1) {
                return -1;
            }
            
            for (int k = found; k > i; k--) {
                swap(rightmost[k], rightmost[k-1]);
                swaps++;
            }
        }
        
        return swaps;
    }
};
