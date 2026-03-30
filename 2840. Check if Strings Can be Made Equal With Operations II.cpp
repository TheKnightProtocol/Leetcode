class Solution {
public:
    bool checkStrings(string s1, string s2) {
        vector<int> evenCount(26, 0);
        vector<int> oddCount(26, 0);
        
        int n = s1.length();
        
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                evenCount[s1[i] - 'a']++;
                evenCount[s2[i] - 'a']--;
            } else {
                oddCount[s1[i] - 'a']++;
                oddCount[s2[i] - 'a']--;
            }
        }
        
        for (int i = 0; i < 26; i++) {
            if (evenCount[i] != 0 || oddCount[i] != 0) {
                return false;
            }
        }
        
        return true;
    }
};
