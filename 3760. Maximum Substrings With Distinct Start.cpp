class Solution {
public:
    int maxDistinct(string s) {
        set<char> set(s.begin(), s.end());
        return set.size();
    }
};
