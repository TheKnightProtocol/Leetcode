class Solution {
public:
    int uniqueLetterString(string s) {
        int n = s.length();
        vector<vector<int>> positions(26);
        
        for (int i = 0; i < 26; i++) {
            positions[i].push_back(-1);
        }
        
        for (int i = 0; i < n; i++) {
            positions[s[i] - 'A'].push_back(i);
        }
        
        for (int i = 0; i < 26; i++) {
            positions[i].push_back(n);
        }
        
        int result = 0;
        
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j < positions[i].size() - 1; j++) {
                int left = positions[i][j] - positions[i][j - 1];
                int right = positions[i][j + 1] - positions[i][j];
                result += left * right;
            }
        }
        
        return result;
    }
};
