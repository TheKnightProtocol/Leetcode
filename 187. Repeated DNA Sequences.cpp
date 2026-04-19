class Solution {
public:
    vector<string> findRepeatedDnaSequences(string s) {
        unordered_map<string, int> freq;
        vector<string> ans;
        int n = s.size();
        for (int i = 0; i + 10 <= n; ++i) {
            string sub = s.substr(i, 10);
            if (++freq[sub] == 2) {
                ans.push_back(sub);
            }
        }
        return ans;
    }
};
