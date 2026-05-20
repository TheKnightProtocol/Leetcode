#include <vector>
using namespace std;

class Solution { 
public:
    vector<int> findThePrefixCommonArray(vector<int>& A, vector<int>& B) {
        int n = A.size();
        vector<bool> seenA(n + 1, false), seenB(n + 1, false);
        vector<int> C(n);
        int common = 0;
        for (int i = 0; i < n; ++i) {
            seenA[A[i]] = true;
            if (seenB[A[i]]) ++common;
            seenB[B[i]] = true;
            if (seenA[B[i]]) ++common;
            C[i] = common;
        }
        return C;
    }
};
