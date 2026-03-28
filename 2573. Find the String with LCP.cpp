class Solution {
public:
    string findTheString(vector<vector<int>>& lcp) {
        int n = lcp.size();
        string word(n, '#');
        
        char currentChar = 'a';
        for (int i = 0; i < n; i++) {
            if (word[i] != '#') continue;
            if (currentChar > 'z') return "";
            word[i] = currentChar;
            for (int j = i + 1; j < n; j++) {
                if (lcp[i][j] > 0) {
                    word[j] = currentChar;
                }
            }
            currentChar++;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int expected = 0;
                while (i + expected < n && j + expected < n && 
                       word[i + expected] == word[j + expected]) {
                    expected++;
                }
                if (expected != lcp[i][j]) {
                    return "";
                }
            }
        }
        
        return word;
    }
};
