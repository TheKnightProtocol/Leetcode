class Solution {
public:
    int bestRotation(vector<int>& nums) {
        int n = nums.size();
        vector<int> change(n + 1, 0);
        
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            
            if (val <= i) {
                change[0]++;
                change[i - val + 1]--;
                change[i + 1]++;
                if (i + 1 + (n - 1 - val) + 1 <= n) {
                    change[i + 1 + (n - 1 - val) + 1]--;
                }
            } else {
                change[i + 1]++;
                if (i + 1 + (n - 1 - val) + 1 <= n) {
                    change[i + 1 + (n - 1 - val) + 1]--;
                }
            }
        }
        
        int maxScore = -1;
        int bestK = 0;
        int currentScore = 0;
        
        for (int k = 0; k < n; k++) {
            currentScore += change[k];
            if (currentScore > maxScore) {
                maxScore = currentScore;
                bestK = k;
            }
        }
        
        return bestK;
    }
};
