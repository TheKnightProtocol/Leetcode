class Solution {
public:
    bool backspaceCompare(string s, string t) {
        int i = s.length() - 1, j = t.length() - 1;
        
        while (i >= 0 || j >= 0) {
            int backspace = 0;
            while (i >= 0 && (s[i] == '#' || backspace > 0)) {
                if (s[i] == '#') backspace++;
                else backspace--;
                i--;
            }
            
            backspace = 0;
            while (j >= 0 && (t[j] == '#' || backspace > 0)) {
                if (t[j] == '#') backspace++;
                else backspace--;
                j--;
            }
            
            if (i < 0 && j < 0) return true;
            if (i < 0 || j < 0) return false;
            if (s[i] != t[j]) return false;
            
            i--;
            j--;
        }
        
        return true;
    }
};
