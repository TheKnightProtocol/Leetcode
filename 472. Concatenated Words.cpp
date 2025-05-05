#include <vector>
#include <string>
#include <unordered_set>
using namespace std;

class Solution {
public:
    bool canForm(string word, unordered_set<string>& dict) {
        if (dict.empty()) return false;
        int n = word.size();
        vector<bool> dp(n + 1, false);
        dp[0] = true;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (dp[j] && dict.count(word.substr(j, i - j))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    vector<string> findAllConcatenatedWordsInADict(vector<string>& words) {
        unordered_set<string> dict;
        vector<string> result;
        sort(words.begin(), words.end(), [](const string& a, const string& b) {
            return a.size() < b.size();
        });
        for (const string& word : words) {
            if (canForm(word, dict)) {
                result.push_back(word);
            }
            dict.insert(word);
        }
        return result;
    }
};
