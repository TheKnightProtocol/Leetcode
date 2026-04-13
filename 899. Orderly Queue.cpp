class Solution {
public:
    string orderlyQueue(string s, int k) {
        if (k > 1) {
            sort(s.begin(), s.end());
            return s;
        }
        
        string smallest = s;
        int n = s.length();
        
        for (int i = 1; i < n; i++) {
            string rotated = s.substr(i) + s.substr(0, i);
            if (rotated < smallest) {
                smallest = rotated;
            }
        }
        
        return smallest;
    }
};
