#include <string>
#include <vector>
using namespace std;

class Solution {
public:
    string generateString(string str1, string str2) {
        int n = str1.length();
        int m = str2.length();
        int L = n + m - 1;
        
        vector<int> fixed(L, -1);
        
        for (int i = 0; i < n; i++) {
            if (str1[i] == 'T') {
                for (int j = 0; j < m; j++) {
                    int pos = i + j;
                    int expected = str2[j] - 'a';
                    
                    if (fixed[pos] == -1) {
                        fixed[pos] = expected;
                    } else if (fixed[pos] != expected) {
                        return "";
                    }
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (str1[i] == 'F') {
                bool canBeDifferent = false;
                
                for (int j = 0; j < m; j++) {
                    int pos = i + j;
                    int expected = str2[j] - 'a';
                    
                    if (fixed[pos] == -1) {
                        canBeDifferent = true;
                        break;
                    } else if (fixed[pos] != expected) {
                        canBeDifferent = true;
                        break;
                    }
                }
                
                if (!canBeDifferent) {
                    return "";
                }
            }
        }
        
        string result(L, 'a');
        
        for (int i = 0; i < L; i++) {
            if (fixed[i] != -1) {
                result[i] = fixed[i] + 'a';
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (str1[i] == 'F') {
                bool equals = true;
                for (int j = 0; j < m; j++) {
                    int pos = i + j;
                    if (result[pos] != str2[j]) {
                        equals = false;
                        break;
                    }
                }
                
                if (equals) {
                    bool fixed_ = false;
                    for (int j = m - 1; j >= 0; j--) {
                        int pos = i + j;
                        if (fixed[pos] == -1) {
                            char nextChar = str2[j] + 1;
                            if (nextChar <= 'z') {
                                result[pos] = nextChar;
                                fixed_ = true;
                                break;
                            }
                        }
                    }
                    
                    if (!fixed_) {
                        return "";
                    }
                }
            }
        }
        
        return result;
    }
};
