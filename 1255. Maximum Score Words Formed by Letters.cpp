#include <vector>
#include <string>
#include <algorithm>
using namespace std;

class Solution {
public:
    int maxScoreWords(vector<string>& words, vector<char>& letters, vector<int>& score) {
        int n = words.size();
        
        vector<int> available(26, 0);
        for (char c : letters) {
            available[c - 'a']++;
        }
        
        vector<vector<int>> wordFreq(n, vector<int>(26, 0));
        vector<int> wordScore(n, 0);
        
        for (int i = 0; i < n; i++) {
            int total = 0;
            for (char c : words[i]) {
                int idx = c - 'a';
                wordFreq[i][idx]++;
                total += score[idx];
            }
            wordScore[i] = total;
        }
        
        int maxScore = 0;
        
        for (int mask = 0; mask < (1 << n); mask++) {
            vector<int> used(26, 0);
            int currentScore = 0;
            bool valid = true;
            
            for (int i = 0; i < n; i++) {
                if (mask & (1 << i)) {
                    for (int j = 0; j < 26; j++) {
                        used[j] += wordFreq[i][j];
                    }
                    currentScore += wordScore[i];
                }
            }
            
            for (int j = 0; j < 26; j++) {
                if (used[j] > available[j]) {
                    valid = false;
                    break;
                }
            }
            
            if (valid) {
                maxScore = max(maxScore, currentScore);
            }
        }
        
        return maxScore;
    }
};
