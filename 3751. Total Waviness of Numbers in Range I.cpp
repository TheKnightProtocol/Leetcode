#include <string>
using namespace std;

class Solution {
public:
    int totalWaviness(int num1, int num2) {
        int ans = 0;
        for (int n = num1; n <= num2; ++n) {
            if (n < 100) continue;
            string s = to_string(n);
            for (int i = 1; i < s.size() - 1; ++i) {
                if ((s[i] > s[i-1] && s[i] > s[i+1]) || 
                    (s[i] < s[i-1] && s[i] < s[i+1]))
                    ++ans;
            }
        }
        return ans;
    }
};
