class Solution {
public:
    int minStickers(vector<string>& stickers, string target) {
        int n = stickers.size();
        vector<vector<int>> stickerCount(n, vector<int>(26, 0));
        
        // Count frequency of each letter in each sticker
        for (int i = 0; i < n; i++) {
            for (char c : stickers[i]) {
                stickerCount[i][c - 'a']++;
            }
        }
        
        // Memoization map: state -> min stickers needed
        unordered_map<string, int> memo;
        memo[""] = 0;
        
        return dfs(target, stickerCount, memo);
    }
    
private:
    int dfs(string target, vector<vector<int>>& stickerCount, unordered_map<string, int>& memo) {
        if (memo.count(target)) {
            return memo[target];
        }
        
        int minStickers = INT_MAX;
        int n = stickerCount.size();
        
        // Count frequency of each letter in target
        vector<int> targetCount(26, 0);
        for (char c : target) {
            targetCount[c - 'a']++;
        }
        
        // Try each sticker
        for (int i = 0; i < n; i++) {
            // Optimization: Skip if sticker doesn't contain the first character of target
            if (stickerCount[i][target[0] - 'a'] == 0) {
                continue;
            }
            
            // Apply this sticker
            string newTarget = "";
            for (int j = 0; j < 26; j++) {
                if (targetCount[j] > stickerCount[i][j]) {
                    newTarget += string(targetCount[j] - stickerCount[i][j], 'a' + j);
                }
            }
            
            int subResult = dfs(newTarget, stickerCount, memo);
            if (subResult != -1) {
                minStickers = min(minStickers, 1 + subResult);
            }
        }
        
        memo[target] = (minStickers == INT_MAX) ? -1 : minStickers;
        return memo[target];
    }
};
