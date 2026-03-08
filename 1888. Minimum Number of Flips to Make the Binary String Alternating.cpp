class Solution {
public:
    int minFlips(string s) {
        int n = s.length();
        
        string target1 = "", target2 = "";
        for (int i = 0; i < 2 * n; i++) {
            if (i % 2 == 0) {
                target1 += '0';
                target2 += '1';
            } else {
                target1 += '1';
                target2 += '0';
            }
        }
        
        string doubled = s + s;
        
        int diff1 = 0, diff2 = 0;
        int minFlips = n;
        
        for (int i = 0; i < 2 * n; i++) {
            if (doubled[i] != target1[i]) diff1++;
            if (doubled[i] != target2[i]) diff2++;
            
            if (i >= n) {
                if (doubled[i-n] != target1[i-n]) diff1--;
                if (doubled[i-n] != target2[i-n]) diff2--;
            }
            
            if (i >= n-1) {
                minFlips = min(minFlips, min(diff1, diff2));
            }
        }
        
        return minFlips;
    }
};
